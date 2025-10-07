package com.itheima.controller;

import com.itheima.AOP.MyLog;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理Controller
 */
@RestController
@Slf4j
public class EmpController {
    @Autowired
    private EmpService empService;


    @GetMapping("/emps")
    public Result selectEmp(@RequestParam(defaultValue = "1")Integer page,
                            @RequestParam(defaultValue = "10")Integer pageSize,
                            String name, Short gender,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询, 参数: {},{},{},{},{},{}",page,pageSize,name,gender,begin,end);
        PageBean pagebean = empService.selectEmp(name,gender,begin,end,page,pageSize);
        //log.info("pagebean_total{}",pagebean.getTotal());
        return Result.success(pagebean);
    }

    @MyLog
    @DeleteMapping("/emps/{ids}")
    public Result deleteEmp(@PathVariable List<Integer> ids){
        log.info(ids.toString());
        empService.deleteEmp(ids);
        return Result.success();
    }

    @MyLog
    @PostMapping("/emps")
    public Result insertEmp(@RequestBody Emp emp){
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empService.insertEmp(emp);
        log.info("收到的emp参数是"+emp.toString());
        return Result.success();
    }

    @GetMapping("/emps/{id}")
    public Result selectEmpById(@PathVariable Integer id){
        Emp emp= empService.selectEmpById(id);
        return Result.success(emp);
    }

    @MyLog
    @PutMapping("/emps")
    public Result updateEmp(@RequestBody Emp emp){
        empService.updateEmp(emp);
        return Result.success();
    }
}
