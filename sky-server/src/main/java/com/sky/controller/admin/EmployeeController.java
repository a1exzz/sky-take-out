package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController//不把返回对象放入视图解析器中 另一种实现方法是使用@Controller 并且在方法上添加@response
@RequestMapping("/admin/employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }
    /**
     * 新增员工
     * @param employeeDTO
     * @return
     */
   @ApiOperation("新增员工")
    @PostMapping//前端请求格式为json 才需要添加@requesBody
    public Result save(@RequestBody EmployeeDTO employeeDTO){
        log.info("新增员工:{}",employeeDTO);
        employeeService.save(employeeDTO);
        return Result.success();

    }

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    @ApiOperation("员工分页查询")
    @GetMapping("/page")
    public  Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("员工分页查询 参数为{}",employeePageQueryDTO);
        PageResult pageResult  =employeeService.pageQurey(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 员工账户启用禁用
     * @param status
     * @param id
     * @return
     */
    @ApiOperation("员工账号使用设置")
    @PostMapping("/status/{status}")
    public  Result startOrStop(@PathVariable Integer status ,long id){// 路径参数前需要添加@PathVariable
        log.info("账号使用 状态参数 {},{}",status,id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }

    /**
     * 通过id查询员工信息
     * @param id
     * @return
     */
    @ApiOperation("查询员工信息")
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable long id){
       Employee employee= employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 编辑员工信息
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("编辑用户信息")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("编辑用户信息{}",employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }


}
