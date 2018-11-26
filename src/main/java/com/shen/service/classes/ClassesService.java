package com.shen.service.classes;

import com.shen.model.ClassesDomain;

import java.util.List;

public interface ClassesService {
    int addClasses(ClassesDomain classes);
    List<ClassesDomain> findAll(int pageNum,int pageSize);
}
