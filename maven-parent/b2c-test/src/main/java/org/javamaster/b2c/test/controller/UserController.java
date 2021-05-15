package org.javamaster.b2c.test.controller;

import org.javamaster.b2c.test.service.UserService;
import org.javamaster.b2c.test.utils.ThreadLocalMapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author yudong
 * @date 2021/2/24
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getName")
    public List<Map<String, String>> getName(@NotNull Integer id) {
        ThreadLocalMapUtils.printThreadLocals();
        return userService.selectActors(Collections.singletonList(id));
    }

}
