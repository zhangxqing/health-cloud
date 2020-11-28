package com.health.system.util;

import com.health.common.utils.security.Md5Utils;
import com.health.system.domain.dto.SysUserDto;

public class PasswordUtil {
    public static boolean matches(SysUserDto userDto, String newPassword) {
        return userDto.getPassword().equals(encryptPassword(userDto.getLoginName(), newPassword, userDto.getSalt()));
    }

    public static String encryptPassword(String username, String password, String salt) {
        return Md5Utils.hash(username + password + salt);
    }


    public static void main(String[] args) {
        String hash = Md5Utils.hash("yangguo1" + "yangguo" + "eJ7dBh");
        System.out.println(hash);
        System.out.println(Md5Utils.hash("yangguo1" + "yangguo" + "eJ7dBh"));
    }
}
