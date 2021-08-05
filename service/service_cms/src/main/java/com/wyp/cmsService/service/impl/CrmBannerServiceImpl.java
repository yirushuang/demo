package com.wyp.cmsService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyp.cmsService.entity.CrmBanner;
import com.wyp.cmsService.mapper.CrmBannerMapper;
import com.wyp.cmsService.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.boot.Banner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-21
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {




    @Cacheable(value = "banner",key = "'selecIndexList'")
    @Override
    public List<CrmBanner> banners() {
        QueryWrapper<CrmBanner> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("sort");
        wrapper.last("limit 2");
        List<CrmBanner> banners = new ArrayList<>();
        banners = baseMapper.selectList(wrapper);
        return banners;
    }
}
