package com.huadi.itmp.modules.user.controller;


import com.huadi.itmp.common.dto.AuthenticationResultDTO;
import com.huadi.itmp.core.annotation.Anonymous;
import com.huadi.itmp.core.annotation.CurrentSubject;
import com.huadi.itmp.core.annotation.PreAuthorize;
import com.huadi.itmp.core.api.Response;
import com.huadi.itmp.core.authentication.Subject;
import com.huadi.itmp.core.enums.SubjectType;
import com.huadi.itmp.modules.user.exception.UserServiceException;
import com.huadi.itmp.modules.user.form.LoginForm;
import com.huadi.itmp.modules.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡学良
 * @since 2021-11-08
 */
@Api(tags = "用户API")
@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @Anonymous
    @ApiOperation("用户登陆")
    @PostMapping("/login")
    public Response<?> login(@RequestBody LoginForm loginForm){

        // 用户名、密码登陆
        AuthenticationResultDTO token;
        try {
            token = userService.login(loginForm.getUsername(), loginForm.getPassword());
            return Response.success(token);
        } catch (UserServiceException e) {
            e.printStackTrace();
            return Response.error(e);
        }
    }

    @ApiOperation("角色拦截测试")
    @PreAuthorize(SubjectType.STUDENT)
    @GetMapping("/test")
    public Response<?> test(@CurrentSubject Subject subject) {
        return Response.success(subject);
    }

}

