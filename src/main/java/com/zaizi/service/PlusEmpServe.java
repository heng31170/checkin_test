package com.zaizi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaizi.mapper.PlusEmpMapper;
import com.zaizi.pojo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlusEmpServe extends ServiceImpl<PlusEmpMapper, Emp> {
    @Autowired
    private PlusEmpMapper plusEmpMapper;
    // 分页查询
    public Page<Emp> getEmpPage(int current,int size) {
        Page<Emp> page = new Page<>(current,size);
        return page(page);
    }
    // 登录
    public Emp login(String account,String passwd) {
        Emp emp = plusEmpMapper.selectByAccount(account);
        if(emp != null && emp.getPasswd().equals(passwd)) {
            return emp;
        }
        return null;
    }
    // 更新密码
    public int updatePasswd(Emp emp) {
        int res = plusEmpMapper.updatePasswd(emp);
        return res;
    }

}
