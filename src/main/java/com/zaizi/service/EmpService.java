package com.zaizi.service;

import com.zaizi.pojo.Emp;

import java.util.List;

public interface EmpService {
    // 获取所有员工
    List<Emp> getAllEmp();
    // 删除员工
    void delEmp(Integer id);
}
