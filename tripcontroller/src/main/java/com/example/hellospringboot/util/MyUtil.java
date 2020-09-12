package com.example.hellospringboot.util;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.lang.Validator;
import cn.hutool.crypto.SecureUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyUtil {

    public static String encodeStringByMd5(String src){
        return SecureUtil.md5(src);
    }

    public static String utilDateToChineseString(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String generateRandomUsername(String mobile){
        return UUID.nameUUIDFromBytes(mobile.getBytes()).toString().replaceAll("-","");
    }
    public static IdentifyType checkIdentifyType(String identify){
        if(Validator.isEmail(identify)){
            return IdentifyType.EMAIL;
        }else if(Validator.isMobile(identify)){
            return IdentifyType.MOBILE;
        }else{
            return IdentifyType.USERNAME;
        }
    }
}
