package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {

    List<Emp> selectEmp(String name, Short gender, LocalDate begin, LocalDate end);

    void deleteEmp(List<Integer> ids);

    void insertEmp(Emp emp);

    @Select("select * from emp where id = #{id}")
    Emp selectEmpById(Integer id);

    void updateEmp(Emp emp);

    @Select("select * from emp where username = #{username}")
    Emp selectEmpByUsername(String username);
}
