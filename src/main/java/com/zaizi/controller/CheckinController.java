package com.zaizi.controller;


import com.zaizi.pojo.Checkin;
import com.zaizi.service.CheckinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class CheckinController {
    @Autowired
    private CheckinService checkinService;
    @GetMapping("api/checkins")
    // 获取打卡状态
    public ResponseEntity<?> getAllCheck() {
        log.info("获取打卡状态");
        try {
            List<Checkin> check = checkinService.getAllCheckin();
            return ResponseEntity.ok(Map.of("isCheck",check));
        } catch (Exception e) {
            log.error("获取失败:{}",e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("error","error"+ e.getMessage()));
        }
    }
    // 添加打卡
    @PostMapping("api/addcheck")
    public ResponseEntity<?> addCheck(@RequestParam(value = "id") Integer empId) {
        log.info("添加打卡");
        checkinService.addCheck(empId);
        return ResponseEntity.ok("success to update check");
    }
    // 取消打卡
    @PostMapping("api/delcheck")
    public ResponseEntity<?> delCheck(@RequestParam(value = "id") Integer empId) {
        log.info("取消打卡");
        checkinService.delCheck(empId);
        return ResponseEntity.ok("success to update unCheck");
    }
}
