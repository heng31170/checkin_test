package com.zaizi.service.impl;

import com.zaizi.mapper.CheckinMapper;
import com.zaizi.pojo.Checkin;
import com.zaizi.service.CheckinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckinServiceImpl implements CheckinService {
    // 获取打卡状态
    @Autowired
    private CheckinMapper checkinMapper;
    @Override
    public List<Checkin> getAllCheckin() {
        return checkinMapper.getAllCheckin();
    }

    // 添加打卡
    @Override
    public void addCheck(Integer empId) {
        checkinMapper.addCheck(empId);
    }
    // 取消打卡
    @Override
    public void delCheck(Integer empId) {
        checkinMapper.delCheck(empId);
    }
}
