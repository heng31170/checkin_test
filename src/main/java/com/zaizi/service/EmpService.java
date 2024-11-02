package com.zaizi.service;

import com.zaizi.pojo.Emp;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    // 获取所有员工
    List<Emp> getAllEmp();
    // 删除员工
    void delEmp(Integer id);
    // 条件查询员工
    List<Emp> getEmp(String name, String gender, LocalDate entryDate);
}
