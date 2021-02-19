package com.javamaster.b2c.cloud.test.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.javamaster.b2c.cloud.test.common.constant.ProjectConst;
import com.javamaster.b2c.cloud.test.common.model.*;
import com.javamaster.b2c.cloud.test.user.constant.AuthorityConst;
import com.javamaster.b2c.cloud.test.user.service.UserService;
import com.javamaster.b2c.cloud.test.user.vo.UpdatePasswordReqVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <ul>
 * <li>returnObject,方法的返回对象</li>
 * <li>filterObject,方法的返回集合中的当前对象</li>
 * </ul>
 * Created on 2018/12/9.<br/>
 *
 * @author yudong
 */
@RestController
@CrossOrigin
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 备用
     *
     * @param users
     * @return
     */
    @PostMapping("/login")
    public Result<Pair<String, UserDetailBean>> login(@Validated @RequestBody Users users, HttpServletResponse response) {
        Pair<String, UserDetailBean> pair = userService.login(users);
        Cookie cookie = new Cookie("SESSION", pair.getKey());
        cookie.setDomain("javamaster.com");
        cookie.setPath("/");
        cookie.setMaxAge(ProjectConst.REDIS_SESSION_EXPIRED_TIME);
        response.addCookie(cookie);
        return new Result<>(true, pair);
    }

    @Secured(AuthorityConst.ROLE_ADMIN)
    @PostMapping("/createUsers")
    public Result<Users> createUsers(@Validated @RequestBody Users users) {
        return new Result<>(true, userService.createUsers(users));
    }

    @Secured(AuthorityConst.ROLE_ADMIN)
    @PostMapping("/disabledUsers")
    public Result<Integer> disabledUsers(@RequestBody JSONObject reqJsonObj) {
        String username = reqJsonObj.getString("username");
        Assert.hasText(username, "100003:用户名不能为空");
        return new Result<>(true, userService.disabledUsers(Users.getInstance().username(username)));
    }

    @Secured(AuthorityConst.ROLE_ADMIN)
    @PostMapping("/enabledUsers")
    public Result<Integer> enabledUser(@RequestBody JSONObject reqJsonObj) {
        String username = reqJsonObj.getString("username");
        Assert.hasText(username, "100003:用户名不能为空");
        return new Result<>(true, userService.enabledUsers(Users.getInstance().username(username)));
    }

    /**
     * 拥有管理员权限可查看任何用户信息,否则只能查看自己的信息
     *
     * @param users
     * @return
     */
    @PreAuthorize("hasAuthority(T(com.javamaster.b2c.cloud.test.user.constant.AuthorityConst).ROLE_ADMIN) or #users.username == #userDetails.username")
    @PostMapping("/findUsers")
    public Result<List<Users>> findUsers(@RequestBody Users users, @AuthenticationPrincipal UserDetails userDetails) {
        PageInfo<Users> pageInfo = userService.findUsers(users);
        return new Result<>(true, pageInfo.getList(), pageInfo.getTotal());
    }

    /**
     * 拥有管理员权限可修改任何用户密码,否则只能修改自己的密码
     *
     * @param reqVo
     * @param userDetails
     * @return
     */
    @ApiOperation(value = "更新用户密码", notes = "拥有管理员权限可修改任何用户密码,否则只能修改自己的密码"
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, response = Result.class, authorizations = {@Authorization(value = "cookie")})
    @PreAuthorize("hasAuthority('ROLE_ADMIN') or (#reqVo.username == #userDetails.username and !T(org.springframework.util.StringUtils).isEmpty(#reqVo.password))")
    @PostMapping("/updatePassword")
    public Result<Integer> updatePassword(@Validated @RequestBody UpdatePasswordReqVo reqVo
            , @ApiIgnore @AuthenticationPrincipal UserDetails userDetails) {
        logger.info("user:{}", userDetails);
        return new Result<>(true, userService.updatePassword(reqVo));
    }

}
