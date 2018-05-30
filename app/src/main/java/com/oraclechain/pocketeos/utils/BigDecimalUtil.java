package com.oraclechain.pocketeos.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pocketEos on 2017/11/23.
 * 高精度加減乘除
 */

public class BigDecimalUtil {
    public static final Map<String, String> CURRENCY_NAME_CODE_PAIRS = new HashMap<String, String>(22);

    static{
        CURRENCY_NAME_CODE_PAIRS.put("澳大利亚元", "AUD");
        CURRENCY_NAME_CODE_PAIRS.put("巴西里亚尔", "BRL");
        CURRENCY_NAME_CODE_PAIRS.put("加拿大元", "CAD");
        CURRENCY_NAME_CODE_PAIRS.put("瑞士法郎", "CHF");
        CURRENCY_NAME_CODE_PAIRS.put("丹麦克朗", "DKK");
        CURRENCY_NAME_CODE_PAIRS.put("欧元", "EUR");
        CURRENCY_NAME_CODE_PAIRS.put("英镑", "GBP");
        CURRENCY_NAME_CODE_PAIRS.put("港币", "HKD");
        CURRENCY_NAME_CODE_PAIRS.put("印尼卢比", "IDR");
        CURRENCY_NAME_CODE_PAIRS.put("日元", "JPY");
        CURRENCY_NAME_CODE_PAIRS.put("韩国元", "KRW");
        CURRENCY_NAME_CODE_PAIRS.put("澳门元", "MOP");
        CURRENCY_NAME_CODE_PAIRS.put("林吉特", "MYR");
        CURRENCY_NAME_CODE_PAIRS.put("挪威克朗", "NOK");
        CURRENCY_NAME_CODE_PAIRS.put("新西兰元", "NZD");
        CURRENCY_NAME_CODE_PAIRS.put("菲律宾比索", "PHP");
        CURRENCY_NAME_CODE_PAIRS.put("卢布", "RUB");
        CURRENCY_NAME_CODE_PAIRS.put("瑞典克朗", "SEK");
        CURRENCY_NAME_CODE_PAIRS.put("新加坡元", "SGD");
        CURRENCY_NAME_CODE_PAIRS.put("泰国铢", "THB");
        CURRENCY_NAME_CODE_PAIRS.put("新台币", "TWD");
        CURRENCY_NAME_CODE_PAIRS.put("美元", "USD");
    }

    /**
     * 判断valA是否大于valB，如果valA大于valB，那么返回true，否则返回false
     * @param valA
     * @param valB
     * @return
     */
    public static boolean greaterThan(BigDecimal valA, BigDecimal valB){
        return (valA.compareTo(valB) > 0);
    }

    /**
     * 判断valA和valB的值是否相等，如果valA和valB的值是否相等，那么返回true，否则返回false
     * @param valA
     * @param valB
     * @return
     */
    public static boolean equals(BigDecimal valA, BigDecimal valB){
        return (valA.compareTo(valB) == 0);
    }

    /**
     * 用于货币计算的加法，返回结果默认精确到小数点后4位，舍入模式：四舍五入
     * @param valA
     * @param valB
     * @return （valA + valB）的结果
     */
    public static BigDecimal add(BigDecimal valA, BigDecimal valB){
        return valA.add(valB).setScale(4, RoundingMode.HALF_UP);
    }
    /**
     * 用于货币计算的加法，返回结果默认精确到小数点后4位，舍入模式：不四舍五入
     * @param valA
     * @param valB
     * @return （valA + valB）的结果
     */
    public static BigDecimal add1(BigDecimal valA, BigDecimal valB){
        return valA.add(valB).setScale(4, RoundingMode.HALF_UP);
    }
    /**
     * 用于货币计算的加法，返回结果的舍入模式：四舍五入
     * @param valA
     * @param valB
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA + valB）的结果
     */
    public static BigDecimal add(BigDecimal valA, BigDecimal valB, int scale){
        return valA.add(valB).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的减法，返回结果默认精确到小数点后4位
     * @param valA
     * @param valB
     * @return （valA - valB）的结果
     */
    public static BigDecimal minus(BigDecimal valA, BigDecimal valB){
        return valA.add(valB.negate()).setScale(4, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的减法，返回结果的舍入模式：四舍五入
     * @param valA
     * @param valB
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA - valB）的结果
     */
    public static BigDecimal minus(BigDecimal valA, BigDecimal valB, int scale){
        return valA.add(valB.negate()).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的乘法，返回结果默认精确到小数点后4位
     * @param valA
     * @param valB
     * @return （valA * valB）的结果
     */
    public static BigDecimal multiply(BigDecimal valA, BigDecimal valB){
        return valA.multiply(valB).setScale(4, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的乘法，返回结果的舍入模式：四舍五入
     * @param valA
     * @param valB
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA * valB）的结果
     */
    public static BigDecimal multiply(BigDecimal valA, BigDecimal valB, int scale){
        return valA.multiply(valB).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的除法，返回结果默认精确到小数点后4位
     * @param valA 被除数
     * @param valB 除数
     * @return （valA / valB）的结果
     */
    public static BigDecimal divide(BigDecimal valA, BigDecimal valB){
        if(BigDecimal.ZERO.compareTo(valB) == 0){
            throw new ArithmeticException("除数不能为0");
        }
        return valA.divide(valB, 4, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的除法，返回结果的舍入模式：四舍五入
     * @param valA 被除数
     * @param valB 除数
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA / valB）的结果
     */
    public static BigDecimal divide(BigDecimal valA, BigDecimal valB, int scale){
        if(BigDecimal.ZERO.compareTo(valB) == 0){
            throw new ArithmeticException("除数不能为0");
        }
        return valA.divide(valB, scale, RoundingMode.HALF_UP);
    }

    /**
     * 将指定的值转换为BigDecimal对象，如果val为null或者为空，那么默认返回0
     * @param val
     */
    public static BigDecimal toBigDecimal(String val){
        if(val == null || "".equals(val.trim())){
            return BigDecimal.ZERO;
        }else{
            return new BigDecimal(val);
        }
    }

}
