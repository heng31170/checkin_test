package com.zaizi.mapper;


import com.zaizi.pojo.Emp;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {
    // 获取所有员工
    @Select("select id, name, image, gender, position, entry_date, last_operation_time, actions from emp")
    List<Emp> getAllEmp();
    // 删除员工
    @Delete("delete from emp where id = #{id}")
    void delEmp(Integer id);
    // 条件查询员工
    List<Emp> getEmp(@Param("name") String name,@Param("gender") String gender,@Param("entry_date") LocalDate entryDate);
}
