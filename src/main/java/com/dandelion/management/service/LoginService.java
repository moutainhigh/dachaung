package com.dandelion.management.service;

import com.dandelion.management.bean.User;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/10/20
 */
public interface LoginService {
    User getUserByName(String name);
}
