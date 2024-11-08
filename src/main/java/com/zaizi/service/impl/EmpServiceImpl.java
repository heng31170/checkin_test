package com.zaizi.service.impl;

import com.zaizi.mapper.EmpMapper;
import com.zaizi.pojo.Checkin;
import com.zaizi.pojo.Emp;
import com.zaizi.service.EmpService;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    @Override
    public void delEmp(Integer id) {
        empMapper.delEmp(id);
        empMapper.delCheckin(id);
    }

    // 条件查询员工

    @Override
    public List<Emp> getEmp(String name, String gender, LocalDate entryDate, String position) {
        return empMapper.getEmp(name,gender,entryDate,position);
    }
    // 根据id查询员工
    @Override
    public Emp getEmpById(Integer id) {
        return empMapper.getEmpById(id);
    }
    // 编辑员工
    @Transactional
    @Override
    public void updateEmp(Emp emp) {
        empMapper.updateEmp(emp);
        String name = emp.getName();
        Integer empId = emp.getId();
        empMapper.updateCheckin(name,empId);
    }
    // 新增员工
    @Transactional
    @Override
    public void addEmpAndCheckin(Emp emp) {
        empMapper.addEmp(emp);
        Integer empId = emp.getId();

        // 创建and插入打卡记录
        Checkin checkin = new Checkin();
        checkin.setEmpId(empId);
        checkin.setEmpName(emp.getName());
        checkin.setIsCheck(false);

        empMapper.addCheckin(checkin);
    }



}


















