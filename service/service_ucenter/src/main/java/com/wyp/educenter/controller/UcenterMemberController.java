package com.wyp.educenter.controller;


import com.wyp.commonutils.R;
import com.wyp.commonutils.utils.JwtUtils;
import com.wyp.educenter.entity.UcenterMember;
import com.wyp.educenter.entity.vo.LoginVo;
import com.wyp.educenter.entity.vo.RegisterVo;
import com.wyp.educenter.service.UcenterMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-23
 */
@RestController
@CrossOrigin
@RequestMapping("/educenter/member")
public class UcenterMemberController {

    @Resource
    private UcenterMemberService ucenterMemberService;

    //登录接口
    @ApiOperation("用户登录接口")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token = ucenterMemberService.login(loginVo);
        return R.ok().data("token",token);
    }

    //注册接口
    @ApiOperation("用户注册接口")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo formItem){
        ucenterMemberService.register(formItem);
        return R.ok();
    }

    //根据token获取用户信息
    @GetMapping("getInfo")
    public R getUserInfo(HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        return R.ok().data("userInfo",member);
    }



}

