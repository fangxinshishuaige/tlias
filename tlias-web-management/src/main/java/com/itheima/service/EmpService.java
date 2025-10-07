package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    PageBean selectEmp(String name, Short gender, LocalDate begin, LocalDate end, Integer page, Integer pageSize);
    void deleteEmp(List<Integer> ids);
    void insertEmp(Emp emp);
    Emp selectEmpById(Integer id);
    void updateEmp(Emp emp);
    Emp selectByUsername(String username);
}
