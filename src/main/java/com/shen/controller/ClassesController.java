package com.shen.controller;

import com.shen.model.ClassesDomain;
import com.shen.service.classes.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/8/16.
 */
@RestController
@RequestMapping(value = "/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @PostMapping("/add")
    public int addClasses(@RequestBody ClassesDomain classes){
        return classesService.addClasses(classes);
    }

    @GetMapping("/all")
    public Object findAllUser(
            @RequestParam(name = "pageNum", required = false, defaultValue = "1")
                    int pageNum,
            @RequestParam(name = "pageSize", required = false, defaultValue = "10")
                    int pageSize){
        //开始分页
      //  PageHelper.startPage(pageNum,pageSize);
        return classesService.findAll(pageNum,pageSize);
    }
}
