package com.dandelion.management.service.impl;

import com.dandelion.management.bean.*;
import com.dandelion.management.mapper.AuthorityGroupMapper;
import com.dandelion.management.mapper.AuthorityMapper;
import com.dandelion.management.mapper.UserMapper;
import com.dandelion.management.service.LoginService;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/10/20
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper mapper;

    @Autowired
    AuthorityMapper authMapper;

    @Autowired
    AuthorityGroupMapper authGroupMapper;

    @Override
    public User getUserByName(String name){
        return login(name);
    }
    /**
     * 从数据库中查找用户名与密码 并将查询结束后数据返回
     * @param account
     * @return
     */
    private User login(String account){
        //单条件查询依据
        Map<String,Object> map = new HashedMap<>();
        map.put("account",account);
        //以固定方式接收返回值
        List<User> user = mapper.selectByMap(map);
        //获取第一个元素
        User u = user.get(0);
        if (u == null){
            //根据Shiro机制,传回Shiro需验证的对象
            return new User();
        }else{
            //若当前已存在 则对user进行权限与角色的绑定操作
            AuthorityGroup group = authGroupMapper.selectById(u.getAuthorityId());
            u.setGroup(group);
            //使用逗号作为分隔依据 进行处理
            String[] authorityNumbers = group.getAuthorityId().split(",");
            List<String> authorities = Arrays.asList(authorityNumbers);
            List<Authority> authorityList = authMapper.selectBatchIds(authorities);
            u.getGroup().setAuthorities(authorityList);
            return u;
        }
    }
    /**
     * 模拟数据库查询
     *
     * @param userName 用户名
     * @return User
     */
    private User getMapByName(String userName) {
        Permissions permissions1 = new Permissions("1", "query");
        Permissions permissions2 = new Permissions("2", "add");
        Set<Permissions> permissionsSet = new HashSet<>();
        permissionsSet.add(permissions1);
        permissionsSet.add(permissions2);
        Role role = new Role("1", "admin", permissionsSet);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = new User();
        user.setId(1);
        user.setName("wsl");
        user.setPassword("123456");
        user.setRoles(roleSet);
        System.out.println(user);
        Map<String, User> map = new HashMap<>();
        map.put(user.getName(), user);

        Set<Permissions> permissionsSet1 = new HashSet<>();
        permissionsSet1.add(permissions1);
        Role role1 = new Role("2", "user", permissionsSet1);
        Set<Role> roleSet1 = new HashSet<>();
        roleSet1.add(role1);
        User user1 = new User();
        user1.setId(2);
        user1.setName("zhangsan");
        user1.setPassword("123456");
        user1.setRoles(roleSet1);
        map.put(user1.getName(), user1);
        return map.get(userName);
    }
}
