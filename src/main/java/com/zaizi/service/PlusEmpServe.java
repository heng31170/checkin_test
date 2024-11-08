package com.zaizi.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zaizi.mapper.PlusEmpMapper;
import com.zaizi.pojo.Emp;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlusEmpServe extends ServiceImpl<PlusEmpMapper, Emp> {
    public Page<Emp> getEmpPage(int current,int size) {
        Page<Emp> page = new Page<>(current,size);
        return page(page);
    }

}
