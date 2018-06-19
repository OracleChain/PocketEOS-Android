package com.oraclechain.pocketeos.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pocketEos on 2017/12/5.
 */
public class StringUtils {
    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     * 小数点后面保留八位,如果是0就直接取0
     * 如果小数点后面不够八位 根据长度动态保留位数
     * 直接筛掉小数点后面的0。
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串 string
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat;
        String pattern = ",###.";
        if (str.contains(".")) {
            if (str.split("\\.")[0].equals("0") && str.split("\\.")[1].length() == 0) {
                decimalFormat = new DecimalFormat("0");
            } else if (str.split("\\.")[0].equals("0") && str.split("\\.")[1].length() != 0) {
                decimalFormat = new DecimalFormat("0.0000");
            } else if (!str.split("\\.")[0].equals("0") && str.split("\\.")[1].length() <= 4) {
                for (int i = 0; i < str.split("\\.")[1].length(); i++) {
                    pattern += "0";
                }
                decimalFormat = new DecimalFormat(pattern);
            } else {
                decimalFormat = new DecimalFormat(",###.0000");
            }
        } else {
            decimalFormat = new DecimalFormat(",###");
        }
        return RegexUtil.subZeroAndDot(decimalFormat.format(Double.parseDouble(str)));
    }


    /**
     * 小数点后四位不足补0
     *
     * @param str the str
     * @return the string
     */
    public static String addZero(String str) {
        DecimalFormat decimalFormat;
        if (str.split("\\.")[0].equals("0")) {
            decimalFormat = new DecimalFormat("0.0000");
        } else
            decimalFormat = new DecimalFormat("###.0000");
        return decimalFormat.format(Double.parseDouble(str));
    }

    /**
     * 去除小数点后面的0
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串 string
     */
    public static String deletzero(String str) {
        DecimalFormat decimalFormat;
        String pattern = "###.";
        if (str.split("\\.")[0].equals("0")) {
            decimalFormat = new DecimalFormat("0");
        } else if (str.split("\\.")[1].length() <= 4) {
            for (int i = 0; i < str.split("\\.")[1].length(); i++) {
                pattern += "0";
            }
            decimalFormat = new DecimalFormat(pattern);
        } else {
            decimalFormat = new DecimalFormat("###.0000");
        }
        return RegexUtil.subZeroAndDot(decimalFormat.format(Double.parseDouble(str)));
    }

    /**
     * String sort string [ ].
     * 字符串数组排序按照字母进行
     *
     * @param s the s
     * @return the string [ ]
     */
    public static String[] stringSort(String[] s) {
        List<String> list = new ArrayList<String>(s.length);
        for (int i = 0; i < s.length; i++) {
            list.add(s[i]);
        }
        Collections.sort(list);
        return list.toArray(s);
    }
}
