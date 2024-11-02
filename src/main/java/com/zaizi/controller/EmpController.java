package com.zaizi.controller;


import com.zaizi.pojo.Emp;
import com.zaizi.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;

    // 获取所有员工
    @GetMapping("/api/emp")
    public ResponseEntity<?> getAllEmp() {
        log.info("获取员工操作");
        List<Emp> empList = empService.getAllEmp();
        return ResponseEntity.ok(Map.of("empList",empList));
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
    public ResponseEntity<?> getEmp(@RequestParam(value = "empname",required = false) String name,
                                    @RequestParam(value = "gender",required = false) String gender,
                                    @RequestParam(value = "start",required = false) LocalDate entryDate) {
        log.info("查询员工,姓名:{},性别:{},入职日期:{}",name,gender,entryDate);
        try {
            List<Emp> empList = empService.getEmp(name,gender,entryDate);
            return ResponseEntity.ok(Map.of("empList",empList));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error","error:"+e.getMessage()));
        }
    }

}
























