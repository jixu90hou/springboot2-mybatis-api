package com.winterchen.dao;

import com.winterchen.model.ClassesDomain;

import java.util.List;

public interface ClassesDao {
    int insert(ClassesDomain record);
    List<ClassesDomain> findAll();
}