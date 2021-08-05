package com.wyp.eduService.controller;


import com.wyp.commonutils.R;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/eduService/user")
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return  R.ok().data("token","admin");
    }

    @GetMapping("getInfo")
    public R getInfo(){
        return  R.ok().data("roles","[admin]").data("name","wyp")
                .data("avatar","http://wyp-9529.oss-cn-beijing.aliyuncs.com/2021/070c6d098f-c3d7-4f56-9c78-d3bf34a7985efile.png");
    }

}
