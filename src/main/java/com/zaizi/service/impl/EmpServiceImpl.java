package com.zaizi.service.impl;

import com.zaizi.mapper.EmpMapper;
import com.zaizi.pojo.Emp;
import com.zaizi.service.EmpService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    // 获取所有员工
    @Override
    public List<Emp> getAllEmp() {
        return empMapper.getAllEmp();
    }

    // 删除员工
    @Override
    public void delEmp(Integer id) {
        empMapper.delEmp(id);
    }

    @Override
    public List<Emp> getEmp(String name, String gender, LocalDate entryDate) {
        return empMapper.getEmp(name,gender,entryDate);
    }
}
