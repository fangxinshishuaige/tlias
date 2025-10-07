package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    List<Emp> list();
    void delete(Integer id);
    void insert(Dept dept);
    Dept selectDept(Integer id);
    void updateDept(Dept dept);
}
