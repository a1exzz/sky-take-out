package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    /**
     * 新增员工
     * @param employeeDTO
     *
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQurey(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 员工账号启用禁用
     * @param status
     * @param id
     */
    void startOrStop(Integer status, long id);

    /**
     * 通过id查询员工信息
     * @param id
     */
    Employee getById(long id);

    /**
     * 编辑用户信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
