package com.zaizi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaizi.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PlusEmpMapper extends BaseMapper<Emp> {
    @Select("select * from emp where account = #{account}")
    Emp selectByAccount(@Param(value = "account") String account);
}
