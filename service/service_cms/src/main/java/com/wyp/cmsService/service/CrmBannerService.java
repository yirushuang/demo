package com.wyp.cmsService.service;

import com.wyp.cmsService.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-21
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> banners();

}
