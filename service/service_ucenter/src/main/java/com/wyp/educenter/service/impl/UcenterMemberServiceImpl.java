package com.wyp.educenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wyp.commonutils.utils.JwtUtils;
import com.wyp.commonutils.utils.MD5;
import com.wyp.educenter.entity.UcenterMember;
import com.wyp.educenter.entity.vo.LoginVo;
import com.wyp.educenter.entity.vo.RegisterVo;
import com.wyp.educenter.mapper.UcenterMemberMapper;
import com.wyp.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyp.servicebase.exception.MyException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2021-07-23
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {


    @Autowired
    private RedisTemplate<String,String> redisTemplate;


    /**
     *  用户登录
     * @param loginVo 登录信息实体类
     * @return  token
     */
    @Override
    public String login(LoginVo loginVo) {
        //如果手机号或者密码为空，抛出异常
        if(StringUtils.isEmpty(loginVo.getMobile()) || StringUtils.isEmpty(loginVo.getPassword())){
                throw new MyException(20001,"手机号和密码不能为空!");
        }

        //根据手机号查询用户信息
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",loginVo.getMobile());
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);

        //如果用户信息为空，则抛出异常
        if(ucenterMember == null){
            throw new MyException(20001,"该用户不存在");
        }

        //判断输入密码是否正确 不正确抛出异常
        System.out.println(MD5.encrypt(loginVo.getPassword()));
        System.out.println(ucenterMember.getPassword());
        if(!MD5.encrypt(loginVo.getPassword()).equals(ucenterMember.getPassword())){
            throw new MyException(20001,"密码错误");
        }

        //判断用户是否禁用
        if(ucenterMember.getIsDeleted()){
            throw new MyException(20001,"该用户无法登录");
        }

        // 获取用户登录token
        String token = JwtUtils.getJwtToken(ucenterMember.getId(),ucenterMember.getNickname());
        System.out.println("token:"+token);
        return token;
    }

    /**
     *  用户注册方法
     * @param registerVo 用户注册信息实体类
     */
    @Override
    public void register(RegisterVo registerVo) {

        //获取用户注册信息
        String nickName = registerVo.getNickname();
        String password = registerVo.getPassword();
        String mobile = registerVo.getMobile();
        String code = registerVo.getCode();

        // 判断用户注册信息是否为空 只要其中一项为空 抛出异常
        if(StringUtils.isEmpty(nickName)||StringUtils.isEmpty(password)||StringUtils.isEmpty(mobile)||StringUtils.isEmpty(code)){
            throw new MyException(20001,"注册信息错误");
        }

        // 判断该手机号是否已经注册
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = baseMapper.selectOne(wrapper);
        if(ucenterMember != null){
            throw new MyException(20001,"该手机号已经注册");
        }

        //从redis中获取 注册验证码
        String redisCode = redisTemplate.opsForValue().get(mobile);
        System.out.println(redisCode);
        if(!redisCode.equals(code)){
            throw new MyException(20001,"验证码错误");
        }
        //将用户信息存入数据库
        UcenterMember member = new UcenterMember();
        member.setNickname(nickName);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setIsDeleted(false);
        this.save(member);
    }
}
