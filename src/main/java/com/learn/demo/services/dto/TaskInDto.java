package com.learn.demo.services.dto;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskInDto {
    private String title;
    private String description;
    private LocalDateTime eta;
}
