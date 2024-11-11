package com.zaizi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zaizi.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PlusEmpMapper extends BaseMapper<Emp> {
    @Select("select * from emp where account = #{account}")
    Emp selectByAccount(@Param(value = "account") String account);
    @Update("update emp set passwd = #{passwd} where passwd = #{originPasswd} and id = #{id}")
    int updatePasswd(Emp emp);
}
