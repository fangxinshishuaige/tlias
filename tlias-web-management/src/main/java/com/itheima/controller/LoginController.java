package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){

        Emp p = empService.selectByUsername(emp.getUsername());
        if(p != null && p.getPassword().equals(emp.getPassword())){
            //log.info("登陆成功");
            HashMap<String,Object> hsmp = new HashMap<>();
            hsmp.put("id",p.getId());
            hsmp.put("username",p.getUsername());
            hsmp.put("name",p.getName());
            String jwt = JwtUtils.generateJwt(hsmp);
            //log.info(jwt);
            return Result.success(jwt);
        }
        else {
            log.info("账号或者密码出错了");
            return Result.error("NOT_LOGINs");
        }
    }
}
