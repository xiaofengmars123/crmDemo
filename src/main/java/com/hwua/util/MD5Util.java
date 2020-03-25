package com.hwua.util;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Util {

    public static String md5hash(String userName,String passwrod){
        ByteSource salt= ByteSource.Util.bytes(userName);
      return  new SimpleHash(Md5Hash.ALGORITHM_NAME,passwrod,salt,1024).toHex();
    }
}
