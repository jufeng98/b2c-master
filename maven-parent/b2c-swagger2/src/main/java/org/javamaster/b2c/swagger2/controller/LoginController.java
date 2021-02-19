package org.javamaster.b2c.swagger2.controller;

import io.swagger.annotations.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.javamaster.b2c.swagger2.model.*;
import org.javamaster.b2c.swagger2.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yudong
 * @date 2019/12/19
 */
@Slf4j
@Api(tags = "登录相关")
@RestController
@RequestMapping("/home")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @ApiOperation("获取应用名")
    @ApiResponses({
            @ApiResponse(code = 200, message = "应用名")
    })
    @GetMapping("/getName")
    public String getName() {
        return "b2c-swagger2";
    }


    @ApiOperation("登录")
    @GetMapping("/login")
    public Result<User> login(UserReqVo userReqVo) {
        return new Result<>(loginService.login(userReqVo));
    }

    @ApiOperation("登录")
    @GetMapping(value = "/login1")
    public Result<User> login1(UserReqVo userReqVo, String appType) {
        return new Result<>(loginService.login(userReqVo, appType));
    }

    @ApiOperation("登录")
    @GetMapping(value = "/login2")
    public Result<User> login2(UserReqVo userReqVo, Integer appType) {
        return new Result<>(loginService.login(userReqVo, appType));
    }

    @ApiOperation("删除素材")
    @PostMapping("/batchDeleteMaterial")
    public Result<List<Integer>> deleteMaterial(@RequestBody @Size(min = 1)
                                                @ApiParam(required = true, allowMultiple = true, examples = @Example(@ExampleProperty(mediaType = "materialIdList", value = "[1,2]")))
                                                        List<Integer> materialIdList) {
        return new Result<>(materialIdList);
    }

    @ApiOperation("查找素材")
    @SneakyThrows
    @GetMapping(value = "/queryMaterial", produces = "application/zip")
    public Result<String> queryMaterial(@RequestParam @ApiParam(example = "1,2", value = "待下载的素材id列表,英文逗号隔开") String ids) {
        List<Integer> materialIdList = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        log.info("{}", ids);
        log.info("{}", materialIdList);
        return new Result<>(ids);
    }
}
