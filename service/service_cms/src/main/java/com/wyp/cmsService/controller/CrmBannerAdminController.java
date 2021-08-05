package com.wyp.cmsService.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyp.cmsService.entity.CrmBanner;
import com.wyp.cmsService.service.CrmBannerService;
import com.wyp.commonutils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/eduCms/adminBanner")
public class CrmBannerAdminController {

    @Resource
    private CrmBannerService crmBannerService;


    /**
     * 查询轮播图
     * @return
     */
    @GetMapping("selectAllBanner")
    public R selectAllBanner(){
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 2");
        List<CrmBanner> crmBanners = new ArrayList<>();
        crmBanners = crmBannerService.list(wrapper);
        return R.ok().data("crmBanners",crmBanners);
    }


    @GetMapping("index")
    public R index(){


        return  R.ok();
    }

}
