package com.zaizi.pojo;


import lombok.Data;

import java.time.LocalDate;

@Data
public class Checkin {
    Integer id;
    String empName;
    Integer empId;
    LocalDate checkDate;
    Boolean isCheck;
}
