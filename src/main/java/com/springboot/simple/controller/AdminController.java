package com.springboot.simple.controller;

import com.springboot.simple.domain.SysAuth;
import com.springboot.simple.domain.SysRole;
import com.springboot.simple.domain.SysUser;
import com.springboot.simple.service.*;
import com.springboot.simple.utils.QueryUserNameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/shiro/")
public class AdminController {

//    @Autowired
//    private AdminService adminService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysAuthService sysAuthService;

    @Autowired
    private SysUserSysRoleSysAuthService sysUserSysRoleSysAuthService;

    @RequestMapping("/queryAllUser")
    @RequiresPermissions("user:admin")
    public String queryAllUser(Model model) {
        List<SysUser> users = sysUserService.querySysUserList();
        model.addAttribute("users", users);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/admin/manageUsers";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user:admin")
    public String delOneUserById(@RequestParam("id") Integer id) {
        sysUserService.delOneSysUserById(id);
        return "redirect:/admin/queryAllUser";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:admin")
    public String updateOneUserById(@RequestParam("sys_user_id") Integer sys_user_id,
                                    @RequestParam("sys_role_id") Integer sys_role_id,
                                    @RequestParam("sys_auth_id") Integer sys_auth_id) {
        // 1.往user_role里面插入数据
        if (sys_user_id != sys_role_id) {
            sysUserSysRoleSysAuthService.saveSysUserSysRole(sys_user_id, sys_role_id);
        }
        // 2.往role_auth里面插入数据
        if (sys_role_id != sys_auth_id) {
            sysUserSysRoleSysAuthService.saveSysRoleSysAuth(sys_role_id, sys_auth_id);
        }
        return "1";
    }

    @RequestMapping("/updateOneUserPage")
    @RequiresPermissions("user:admin")
    public String gotoUpdateOneUserPage(@RequestParam("id") Integer id, Model model) {
        SysUser user = sysUserService.querySysUserById(id);
        model.addAttribute("user", user);
        List<SysRole> roleList = sysRoleService.querySysRoleList();
        model.addAttribute("roleList", roleList);
        List<SysAuth> authList = sysAuthService.querySysAuthList();
        model.addAttribute("authList", authList);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/admin/updateOneUser";
    }

    @RequestMapping("/delete/role")
    @ResponseBody
    @RequiresPermissions("user:admin")
    public String delRole(@RequestParam("sys_user_id") Integer sys_user_id
            , @RequestParam("sys_role_id") Integer sys_role_id) {
        sysUserSysRoleSysAuthService.delSysRole(sys_user_id, sys_role_id);
        return "1";
    }

    @RequestMapping("/delete/auth")
    @ResponseBody
    @RequiresPermissions("user:admin")
    public String delAuth(@RequestParam("sys_auth_id") Integer sys_auth_id
            , @RequestParam("sys_role_id") Integer sys_role_id) {
        sysUserSysRoleSysAuthService.delSysAuth(sys_role_id, sys_auth_id);
        return "1";
    }

}
