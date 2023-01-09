package com.learn.demo.mapper;

public interface IMapper <I, O>{
    O map(I in);
}
