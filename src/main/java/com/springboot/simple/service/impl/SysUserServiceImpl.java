package com.springboot.simple.service.impl;

import com.springboot.simple.dao.SysUserDao;
import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.domain.SysRole;
import com.springboot.simple.domain.SysUser;
import com.springboot.simple.service.SysUserService;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUser> querySysUserList() {
        List<SysUser> userList = sysUserDao.queryAllSysUsers();
        List<SysUser> users = new ArrayList<>();
        for (SysUser user:userList) {
            // 2.查找用户对应的角色
            List<SysRole> roleList = new ArrayList<>();
            Integer role_id = sysUserDao.querySysRoleIdByUserId(user.getId());
            SysRole role = sysUserDao.querySysRoleByRoleId(role_id);

            // 3.查找角色对应的auth
            List<Integer> authIdList = null;
            try {
                authIdList = sysUserDao.querySysAuthIdByRoleId(role.getId());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("查询不到用户权限Id");
            }
            List<SysAuth> authList = new ArrayList<>();
            if (authIdList != null) {
                for (Integer id:authIdList) {
                    SysAuth auth = null;
                    try {
                        auth = sysUserDao.querySysAuthByAuthId(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("没有权限信息");
                    }
                    authList.add(auth);
                }
            }

            // 4.组装
            if (role != null) {
                role.setSysAuthList(authList);
            }
            roleList.add(role);
            user.setSysRoleList(roleList);
            users.add(user);
        }
        return users;
    }

    @Override
    public SysUser querySysUserByUserName(String username) {
        // 1.查询用户
        SysUser user = sysUserDao.querySysUserByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();
        }

        // 2.查找用户对应的角色
        List<SysRole> roleList = new ArrayList<>();
        Integer role_id = sysUserDao.querySysRoleIdByUserId(user.getId());
        SysRole role = sysUserDao.querySysRoleByRoleId(role_id);

        // 3.查找角色对应的auth
        List<Integer> authIdList = null;
        try {
            authIdList = sysUserDao.querySysAuthIdByRoleId(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询不到用户权限Id");
        }
        List<SysAuth> authList = new ArrayList<>();
        if (authIdList != null) {
            for (Integer id:authIdList) {
                SysAuth auth = null;
                try {
                    auth = sysUserDao.querySysAuthByAuthId(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("没有权限信息");
                }
                authList.add(auth);
            }
        }

        // 4.组装
        if (role != null) {
            role.setSysAuthList(authList);
        }
        roleList.add(role);
        user.setSysRoleList(roleList);
        return user;
    }

    @Override
    public SysUser querySysUserById(Integer Id) {
        // 1.查询用户
        SysUser user = sysUserDao.querySysUserById(Id);

        if (user == null) {
            throw new UnknownAccountException();
        }

        // 2.查找用户对应的角色
        List<SysRole> roleList = new ArrayList<>();
        Integer role_id = sysUserDao.querySysRoleIdByUserId(user.getId());
        SysRole role = sysUserDao.querySysRoleByRoleId(role_id);

        // 3.查找角色对应的auth
        List<Integer> authIdList = null;
        try {
            authIdList = sysUserDao.querySysAuthIdByRoleId(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询不到用户权限Id");
        }
        List<SysAuth> authList = new ArrayList<>();
        if (authIdList != null) {
            for (Integer id:authIdList) {
                SysAuth auth = null;
                try {
                    auth = sysUserDao.querySysAuthByAuthId(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("没有权限信息");
                }
                authList.add(auth);
            }
        }

        // 4.组装
        if (role != null) {
            role.setSysAuthList(authList);
        }
        roleList.add(role);
        user.setSysRoleList(roleList);
        return user;
    }

    @Override
    public void saveOneSysUser(SysUser user) {
        try {
            sysUserDao.saveOneSysUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("======注册失败======");
        }
    }

    @Override
    public void delOneSysUserById(Integer id) {
        sysUserDao.delOneSysUserById(id);
    }
}
