package com.ssm.common.utils;

import com.alibaba.druid.filter.config.ConfigTools;

public class PwdEncOrDectUtil {

    public static String encryptPasswd(String passwd) {
        try {
            return ConfigTools.encrypt(passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decryptPasswd (String passwd) {
        try {
            return ConfigTools.decrypt(passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String sts = "tiger";
        System.out.println(encryptPasswd(sts));
    }
}
