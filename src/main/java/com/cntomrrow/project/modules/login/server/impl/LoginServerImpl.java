package com.cntomrrow.project.modules.login.server.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.api.R;
import com.cntomrrow.project.modules.login.entity.SysUser;
import com.cntomrrow.project.modules.login.mapper.LoginMapper;
import com.cntomrrow.project.modules.login.server.LoginServer;
import com.cntomrrow.project.modules.utils.Tools;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @Author: zkx
 * @date: 31/1/2023 下午7:47
 */

@Service
public class LoginServerImpl implements LoginServer {

    @Autowired
    private LoginMapper loginMapper;

    @Override
    public Map getUer() {
        return loginMapper.getUer();
    }

    /**
     * 用户注册
     * @param sysUser
     * @return
     */
    @Override
    public R register(SysUser sysUser) {
        if ((sysUser.getPhone()).isEmpty()) return R.failed("手机号不能为空！");
        if ((sysUser.getUuid()).isEmpty()) return R.failed("积分地址不能为空！");
        if ((sysUser.getPassword()).isEmpty()) return R.failed("密码不能为空！");
        if (Pattern.matches("^(13[0-9]|14[01456879]|15[0-35-9]|16[2567]|17[0-8]|18[0-9]|19[0-35-9])\\d{8}$",sysUser.getPhone())){
        //校验手机号是否存在
            QueryWrapper queryWrapper=new QueryWrapper();
            queryWrapper.eq("phone",sysUser.getPhone());
            Integer integer = loginMapper.selectCount(queryWrapper);
            if (integer>0) return R.failed("手机号已注册！");
            //生产随机字符当做盐值
            String randomNumber = Tools.randomNumber(6);
            String md5Password="";
            String md5Hex = DigestUtils.md5Hex(sysUser.getPassword() + randomNumber);
            sysUser.setSalt(randomNumber);
            sysUser.setReal_password(sysUser.getPassword());
            sysUser.setPassword(md5Hex);
            sysUser.setStatus(1);
            loginMapper.insert(sysUser);
        }else{
          return R.failed("手机号格式不正确！");
        }
        return R.ok("");
    }

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    @Override
    public R login(SysUser sysUser) {
        if ((sysUser.getPhone()).isEmpty()) return R.failed("手机号不能为空！");
        if ((sysUser.getPassword()).isEmpty()) return R.failed("密码不能为空！");
        SysUser sysUser1 = loginMapper.selectById(sysUser.getUuid());
        if (sysUser1==null)return R.failed("用户不存在");
        String user1Password = sysUser1.getPassword();
        if (sysUser1.getStatus()!=1) return R.failed("此账号已冻结！");
        String salt = sysUser1.getSalt();
        String md5Hex = DigestUtils.md5Hex(sysUser.getPassword() + salt);
        if (!md5Hex.equals(user1Password)){
            return R.failed("密码不正确");
        }
        return R.ok("");
    }

    /**
     * 重置密码
     * @param sysUser
     * @return
     */
    @Override
    public R resetPassword(SysUser sysUser) {
        if ((sysUser.getPhone()).isEmpty()) return R.failed("手机号不能为空！");
        if ((sysUser.getPassword()).isEmpty()) return R.failed("密码不能为空！");
        Map map=new HashMap(){{
            put("phone",sysUser.getPhone());
        }};
        List<SysUser> list = loginMapper.selectByMap(map);
        if (list.size()==0) return R.failed("手机号码不正确！");
        SysUser sysUser1 = list.get(0);
        String salt = sysUser1.getSalt();
        String md5Hex = DigestUtils.md5Hex(sysUser.getPassword() + salt);
        sysUser1.setPassword(md5Hex);
        sysUser1.setReal_password(sysUser.getPassword());
        loginMapper.updateById(sysUser1);
        return R.ok("密码已重置");
    }
}
