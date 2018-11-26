package com.shen.dao;

import com.shen.model.ClassesDomain;

import java.util.List;

public interface ClassesDao {
    int insert(ClassesDomain record);
    List<ClassesDomain> findAll();
}