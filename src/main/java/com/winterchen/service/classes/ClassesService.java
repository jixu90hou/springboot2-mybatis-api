package com.winterchen.service.classes;

import com.winterchen.model.ClassesDomain;

import java.util.List;

public interface ClassesService {
    int addClasses(ClassesDomain classes);
    List<ClassesDomain> findAll(int pageNum,int pageSize);
}
