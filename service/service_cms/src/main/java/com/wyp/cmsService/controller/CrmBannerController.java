package com.wyp.cmsService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyp.commonutils.R;
import com.wyp.cmsService.entity.CrmBanner;
import com.wyp.cmsService.service.CrmBannerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-07-21
 */
@RestController
@RequestMapping("/eduCms/banner")
@CrossOrigin
public class CrmBannerController {

    @Resource
    private CrmBannerService crmBannerService;


    /**
     * 查询所有轮播图
     * @return
     */
    @GetMapping("selectAllBanner")
    public R selectAllBanner(){
        List<CrmBanner> crmBanners = new ArrayList<>();
        crmBanners = crmBannerService.banners();
        return R.ok().data("list",crmBanners);
    }

    /**
     *
     * @return
     */
    @GetMapping("index")
    public R index(){

        return  R.ok();
    }

}

