package com.zaizi.mapper;


import com.zaizi.pojo.Checkin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CheckinMapper {
    // 获取所有打卡状态
    @Select("select id,emp_name,emp_id,check_date,is_check from daily_checkin")
    List<Checkin> getAllCheckin();
    // 确定打卡
    @Update("UPDATE daily_checkin AS d\n" +
            "JOIN emp AS e ON d.emp_id = e.id\n" +
            "SET d.is_check = true, d.check_date = CURDATE()\n" +
            "WHERE e.id = #{empId} ")
    void addCheck(Integer empId);
    // 取消打卡
    @Update("UPDATE daily_checkin AS d\n" +
            "JOIN emp AS e ON d.emp_id = e.id\n" +
            "SET d.is_check = false, d.check_date = CURDATE()\n" +
            "WHERE e.id = #{empId} ")
    void delCheck(Integer empId);
}
