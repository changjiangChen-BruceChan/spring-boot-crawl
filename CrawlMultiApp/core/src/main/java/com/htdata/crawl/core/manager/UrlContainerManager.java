package com.htdata.crawl.core.manager;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.util.HashSet;

/**
 * url去重
 */
@Service
public class UrlContainerManager {

    private HashSet<String> hashSet = new HashSet<>();

    private String md5(String string) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        try {
            byte[] bytes = string.getBytes();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bytes);
            byte[] updateBytes = messageDigest.digest();
            int len = updateBytes.length;
            char myChar[] = new char[len * 2];
            int k = 0;
            for (int i = 0; i < len; i++) {
                byte byte0 = updateBytes[i];
                myChar[k++] = hexDigits[byte0 >>> 4 & 0x0f];
                myChar[k++] = hexDigits[byte0 & 0x0f];
            }
            return new String(myChar);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean urlExists(String url) {
        return hashSet.contains(md5(url.toLowerCase()));
    }

    public void storeUrlToSet(String url) {
        hashSet.add(md5(url.toLowerCase()));
    }
}
