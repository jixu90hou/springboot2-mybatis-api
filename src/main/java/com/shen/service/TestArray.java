package com.shen.service;

import java.util.ArrayList;
import java.util.List;

public class TestArray {
    public static void main(String[] args) {
        List<ArrayEntity> entities=new ArrayList<>(3);
        ArrayEntity entity1=new ArrayEntity(1,"entity1");
        ArrayEntity entity2=new ArrayEntity(2,"entity2");
        ArrayEntity entity3=new ArrayEntity(3,"entity3");
        entities.add(entity1);
        entities.add(entity2);
        entities.add(entity3);

        List<ArrayEntity> newEntities=new ArrayList<>(3);
        // ArrayEntity newArrayEntity
        entities.stream().forEach(s->{
            ArrayEntity newArrayEntity=new ArrayEntity(s.getId(),s.getName());
            newArrayEntity.setId(s.getId()+10);
            newEntities.add(newArrayEntity);
        });


    }
}
class ArrayEntity{
    private int id;
    private String name;

    public ArrayEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}