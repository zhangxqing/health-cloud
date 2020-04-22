package com.health.system.util;

import com.health.common.utils.security.Md5Utils;
import com.health.system.domain.SysUser;

public class PasswordUtil {
    public static boolean matches(SysUser user, String newPassword) {
        return user.getPassword().equals(encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }


    public static void main(String[] args) {
        String hash = Md5Utils.hash("zhangqing" + "zhangqing0718" + "0718");
        System.out.println(hash);
        System.out.println(Md5Utils.hash("zhangqing" + "zhangqing0718" + "0718"));
    }
}
