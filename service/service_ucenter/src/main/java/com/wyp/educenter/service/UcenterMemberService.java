package com.wyp.educenter.service;

import com.wyp.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyp.educenter.entity.vo.LoginVo;
import com.wyp.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2021-07-23
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(LoginVo loginVo);

    void register(RegisterVo registerVo);
}
