package com.zaizi.mapper;


import com.zaizi.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    // 获取所有员工
    @Select("select id, name, image, gender, position, entry_date, last_operation_time, actions from emp")
    List<Emp> getAllEmp();
    // 删除员工
    @Delete("delete from emp where id = #{id}")
    void delEmp(Integer id);
}
