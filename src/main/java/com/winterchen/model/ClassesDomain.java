package com.winterchen.model;

import java.util.List;

public class ClassesDomain {
    private Integer id;
    private String className;
    private Integer countUser;
    private List<UserDomain> userDomainList;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public List<UserDomain> getUserDomainList() {
        return userDomainList;
    }

    public void setUserDomainList(List<UserDomain> userDomainList) {
        this.userDomainList = userDomainList;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getCountUser() {
        return countUser;
    }

    public void setCountUser(Integer countUser) {
        this.countUser = countUser;
    }
}