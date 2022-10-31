package com.example.forum.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * @author 86188
 */
public class CommonUtil {
    public static String mD5(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString().toUpperCase();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static int transformation(String data) {
        return switch (data) {
            case "JavaWeb" -> 1;
            case "Web前端" -> 2;
            case "人工智能" -> 3;
            case "自动化测试" -> 4;
            case "大数据" -> 5;
            case "Java开发" -> 6;
            case "数据库" -> 7;
            case "后端架构" -> 8;
            default -> 0;
        };
    }
}
