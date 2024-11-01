package com.zaizi.pojo;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Emp {
    Integer id;
    String name;
    String image;
    String gender;
    String position;
    LocalDate entryDate;
    LocalDateTime lastOperationTime;
    String action;
}
