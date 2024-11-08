package com.zaizi.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaizi.pojo.Emp;
import com.zaizi.service.EmpService;
import com.zaizi.service.PlusEmpServe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    @Autowired
    private PlusEmpServe plusEmpServe;

    // 获取所有员工
    @GetMapping("/api/emp")
    public ResponseEntity<?> getAllEmp() {
        log.info("获取员工操作");
        List<Emp> empList = empService.getAllEmp();
        return ResponseEntity.ok(Map.of("empList",empList));
    }
    // 获取分页查询员工
    @GetMapping("/api/emp/page")
    public Page<Emp> getEmpByPage(@RequestParam(value = "current",defaultValue = "1") int current,
                                  @RequestParam(value = "size",defaultValue = "5") int size) {
        log.info("分页查询员工,当前页:{},尺寸:{}",current,size);
        Page<Emp> page = plusEmpServe.getEmpPage(current,size);
        log.info("返回数据:{}",page);
        return page;
    }

    // 根据id删除员工
    @PostMapping("/api/emp/delete")
    public ResponseEntity<?> delEmp(@RequestParam("id") Integer id) {
        log.info("删除员工操作,员工id:{}",id);
        try {
            empService.delEmp(id);
            return ResponseEntity.ok("删除成功");
        } catch (Exception e) {
            log.error("删除员工失败,原因是:" + e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error:","error"+e.getMessage()));
        }
    }
    // 条件查询员工
    @GetMapping("/api/emp/search")
    public ResponseEntity<?> getEmp(@RequestParam(value = "name",required = false) String name,
                                    @RequestParam(value = "gender",required = false) String gender,
                                    @RequestParam(value = "entryDate",required = false)
                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entryDate,
                                    @RequestParam(value = "position") String position) {
        log.info("查询员工,姓名:{},职位:{},性别:{},入职日期:{}",name,position,gender,entryDate);
        try {
            List<Emp> empList = empService.getEmp(name,gender,entryDate,position);
            return ResponseEntity.ok(Map.of("empList",empList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }
    // 根据id查询员工
    @GetMapping("/api/emp/{id}")
    public ResponseEntity<?> getEmpById(@PathVariable Integer id) {
        log.info("根据id查询员工,id:{}",id);
        Emp emp = empService.getEmpById(id);
        return ResponseEntity.ok(emp);
    }
    // 编辑员工
    private static final String UPLOAD_DIR = "D:/Javaidea/springboot_learn/checkin_test/src/main/resources/static/";
    private static final String SERVE_DIR = "http://127.0.0.1:5678/";
    @PostMapping("/api/emp/update")
    public ResponseEntity<?> updateEmp(@RequestParam(value = "file", required = false) MultipartFile file,
                                       @RequestParam("json") String json) {
        try {
            // 解析 JSON 数据
            ObjectMapper objectMapper = new ObjectMapper();
            Emp emp = objectMapper.readValue(json, Emp.class);

            // 如果文件不为空，处理文件上传
            if (file != null && !file.isEmpty()) {
                log.info("接收到文件:{}",file.getOriginalFilename());
                String fileUrl = uploadFile(file,emp); // 上传文件并获取 URL
                emp.setImage(fileUrl); // 设置员工头像 URL
            } else {
                log.warn("没有接受到文件");
            }

            empService.updateEmp(emp); // 更新员工信息
            return ResponseEntity.ok("success to update emp");
        } catch (Exception e) {
            log.error("更新员工信息失败: {}", e.getMessage());
            return ResponseEntity.status(500).body("更新员工信息失败: " + e.getMessage());
        }
    }
    // 新增员工
    @PostMapping("/api/emp/add")
    public ResponseEntity<?> addEmpAndCheck(@RequestBody Emp emp) {
        log.info("新增员工操作,员工:{}",emp);
        try {
            empService.addEmpAndCheckin(emp);
            return ResponseEntity.ok("success to add emp");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("员工添加失败:",e.getMessage()));
        }
    }


    // 文件上传
    private String uploadFile(MultipartFile file,Emp emp) throws Exception {

        // 文件上传逻辑
        String empDir = String.valueOf(emp.getId());
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//        Path filePath = Paths.get(UPLOAD_DIR).resolve(fileName);
        Path filePath = Paths.get(UPLOAD_DIR).resolve(empDir).resolve(fileName);

        // 创建父目录（如果不存在）
        Files.createDirectories(filePath.getParent());

        Files.copy(file.getInputStream(), filePath);
        return SERVE_DIR + empDir + "/" + fileName; // 返回文件 URL
    }

}

























