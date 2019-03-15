package com.example;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * shiro使用步骤
 *
 * 1. 创建shiro配置文件, 按照格式注入[users][roles], 并关联其关系 ----initTest()
 *    常用的方式是自定义 realm 向 securityManager 注入数据库数据 ----fromDb()
 * 2. 加载配置文件创建SecurityManager工厂并获取一个实例
 * 3. 关联 SecurityUtils 和 SecurityManager
 * 4. 使用 SecurityUtils 获取交互对象 Subject
 * 5. 将需要认证的信息封装为 token 传入 Subject 进行交互
 */
public class iniTest {

    @Test   //ini 配置文件实现认证, 需要在配置文件中配置用户和角色以及权限的对应关系
    public void fromIni()
    {
        //使用ini配置文件创建SecurityManage工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-permission.ini");

        //进行 Subject 交互认证
        this.auth(factory);
    }

    @Test   //自定义 realm 实现认证, 需要查数据库并封装用户权限信息给 securityManager
    public void fromDb()
    {
        //加载配置文件, 文件中定义 realm 的实现类
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-reamls.ini");

        //进行 Subject 交互认证
        this.auth(factory);
    }

    //交互认证
    private void auth(Factory<SecurityManager> factory)
    {
        //获取一个SecurityManage实例
        SecurityManager instance = factory.getInstance();

        //关联SecurityUtils
        SecurityUtils.setSecurityManager(instance);

        //获取Subjet
        Subject subject = SecurityUtils.getSubject();

        //准备请求数据, 这里使用最常见的用户名密码
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");

        try {
            //使用subject进行交互验证
            subject.login(token);
        }catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登陆失败");
            return;
        }

        System.out.println("登陆成功");

        //必须通过认证, 才能进行角色和权限的操作

        //判断用户是否拥有角色
        System.out.println(subject.hasRole("role1"));

        //检查用户是否拥有角色, 没有返回值, 如果有不做任何操作, 如果没有则抛出异常
        subject.checkRoles("role1", "role2");

        //判断用户是否拥有权限
        System.out.println(subject.isPermitted("user:create"));

        //检查用户是否拥有权限, 没有返回值, 如果有不做任何操作, 如果没有则抛出异常
        subject.checkPermission("user:create");

        subject.logout();
    }
}
