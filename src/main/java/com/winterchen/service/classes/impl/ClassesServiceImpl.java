package com.winterchen.service.classes.impl;

import com.github.pagehelper.PageHelper;
import com.winterchen.dao.ClassesDao;
import com.winterchen.model.ClassesDomain;
import com.winterchen.service.classes.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("classesService")
public class ClassesServiceImpl implements ClassesService{
    @Autowired
    private ClassesDao classesDao;
    @Override
    public int addClasses(ClassesDomain classes) {
        return classesDao.insert(classes);
    }

    @Override
    public List<ClassesDomain> findAll(int pageNum,int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
     //   PageHelper.startPage(pageNum, pageSize);
        return classesDao.findAll();
    }
}
