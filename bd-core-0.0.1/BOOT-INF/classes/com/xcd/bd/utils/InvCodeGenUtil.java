package com.xcd.bd.utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Project : ffprSystem
 * @Description : 注册码生成工具
 * @Author : ljk
 * @Date : 2018/10/17  23:11
 * @ModificationHistory Who          When          What
 * ----------   ------------- -----------------------------------
 * ljk          2018/10/17    create
 */
public class InvCodeGenUtil {
    private static final char[] r=new char[]{'q', 'w', 'e', '8', 's', '2', 'd', 'z', 'x', '9', 'c', '7', 'p', '5', 'k', '3', 'm', 'j', 'u', 'f', 'r', '4', 'v', 'y', 't', 'n', '6', 'b', 'g', 'h'};

    /** 定义一个字符用来补全邀请码长度（该字符前面是计算出来的邀请码，后面是用来补全用的） */
    private static final char b='a';

    /** 进制长度 */
    private static final int binLen=r.length;

    /** 邀请码长度 */
    private static final int s=6;


    /**
     * 补位字符串
     */
    private static final String e = "atgsghj";

    /**
     * 根据ID生成六位随机码
     *
     * @param id ID
     * @return 随机码
     */
    public static String toSerialCode(long id) {
        char[] buf = new char[32];
        int charPos = 32;

        while ((id / binLen) > 0) {
            int ind = (int) (id % binLen);
            buf[--charPos] = r[ind];
            id /= binLen;
        }
        buf[--charPos] = r[(int) (id % binLen)];
        String str = new String(buf, charPos, (32 - charPos));
        // 不够长度的自动补全
        if (str.length() < s) {
            StringBuilder sb = new StringBuilder();
            sb.append(e.subSequence(0, s - str.length()));
            str += sb.toString();
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        String str = toSerialCode(System.currentTimeMillis());
        System.out.println(str);
    }
}
