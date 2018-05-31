package com.oraclechain.pocketeos.utils;

import java.text.DecimalFormat;

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
     * <b>Summary: 忽略大小写比较两个字符串</b>
     * ignoreCaseEquals()
     *
     * @param str1 the str 1
     * @param str2 the str 2
     * @return boolean
     */
    public static boolean ignoreCaseEquals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
    }

}
