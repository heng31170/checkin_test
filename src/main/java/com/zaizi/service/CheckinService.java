package com.zaizi.service;

import com.zaizi.pojo.Checkin;

import java.util.List;

public interface CheckinService {
    // 获取打卡状态
    List<Checkin> getAllCheckin();
    // 添加打卡
    void addCheck(Integer empId);
    // 取消打卡
    void delCheck(Integer empId);
}
