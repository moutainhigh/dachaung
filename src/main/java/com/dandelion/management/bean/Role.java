package com.dandelion.management.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * Class description
 *
 * @author hongjianYang
 * @date 2020/10/20
 */
@Data
@AllArgsConstructor
public class Role {
    private String id;
    private String roleName;
    /**
     * 角色对应权限集合
     */
    private Set<Permissions> permissions;
}
