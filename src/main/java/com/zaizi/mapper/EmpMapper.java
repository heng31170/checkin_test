package com.zaizi.mapper;


import com.zaizi.pojo.Checkin;
import com.zaizi.pojo.Emp;
import org.apache.ibatis.annotations.*;

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
    // 删除打卡信息
    @Delete("delete from daily_checkin where emp_id = #{empId}")
    void delCheckin(Integer empId);
    // 条件查询员工
    List<Emp> getEmp(@Param("name") String name,@Param("gender") String gender,@Param("entry_date") LocalDate entryDate);
    // 根据id查询员工
    @Select("select * from emp where id = #{id}")
    Emp getEmpById(Integer id);
    // 编辑员工
    void updateEmp(Emp emp);
    // 更新打卡名字
    void updateCheckin(@Param("empName") String empName,@Param("empId") Integer empId);
    // 新增员工&打卡
    @Insert("insert into emp (name, gender, position, entry_date, last_operation_time)" +
            "values (#{name}, #{gender}, #{position}, #{entryDate}, NOW())")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void addEmp(Emp emp);
    @Insert("insert into daily_checkin (emp_name, emp_id, check_date, is_check)\n" +
            "        VALUES (#{empName}, #{empId}, CURDATE(), #{isCheck})")
    void addCheckin(Checkin checkin);

}
