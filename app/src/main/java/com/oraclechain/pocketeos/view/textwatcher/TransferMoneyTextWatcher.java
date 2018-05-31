package com.oraclechain.pocketeos.view.textwatcher;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.StringUtils;

import java.math.BigDecimal;

/**
 * Created by pocketEos on 2018/2/3.
 * 监听edittext文本框输入限制小数点后面四位
 * 1.首位输入.的时候,补全为0.
 * <p>
 * 2.删除“.”后面超过4位后的数据
 * <p>
 * 3.如果起始位置为0,且第二位跟的不是".",则无法后续输入
 */

public class TransferMoneyTextWatcher implements TextWatcher {
    private EditText editText;
    private EditText editText2;
    private Button button;
    private TextView editText1;//随之变化的人民币金额框
    private int digits = 4;
    private BigDecimal coinRate;

    public TransferMoneyTextWatcher(EditText et, TextView et1, BigDecimal rate, EditText et2, Button btn) {
        editText = et;
        editText1 = et1;
        coinRate = rate;
        editText2 = et2;
        button = btn;
    }

    public TransferMoneyTextWatcher setDigits(int d) {
        digits = d;
        return this;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //删除“.”后面超过4位后的数据
        if (s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") > digits) {
                s = s.toString().subSequence(0,
                        s.toString().indexOf(".") + digits + 1);
                editText.setText(s);
                editText.setSelection(s.length()); //光标移到最后
            }
        }
        //如果"."在起始位置,则起始位置自动补0
        if (s.toString().trim().substring(0).equals(".")) {
            s = "0" + s;
            editText.setText(s);
            editText.setSelection(2);
        }

        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (s.toString().startsWith("0")
                && s.toString().trim().length() > 1) {
            if (!s.toString().substring(1, 2).equals(".")) {
                editText.setText(s.subSequence(0, 1));
                editText.setSelection(1);
                return;
            }
        }
        if (s.toString().length() != 0 && !s.toString().contains(".")) {
            editText1.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(BigDecimal.valueOf(Double.parseDouble(s.toString())), coinRate, 4) + "") + "CNY");
            if (editText2.getText().toString().trim().length() != 0) {
                button.setBackgroundColor(Color.parseColor("#4D7BFE"));
            } else {
                button.setBackgroundColor(Color.parseColor("#DDDDDD"));
            }
        } else if (s.toString().length() != 0 && s.toString().contains(".")) {
            if (s.length() - 1 - s.toString().indexOf(".") >= 0) {
                editText1.setText("≈" + StringUtils.addComma(BigDecimalUtil.multiply(BigDecimal.valueOf(Double.parseDouble(s.toString())), coinRate, 4) + "") + "CNY");
                if (editText2.getText().toString().trim().length() != 0) {
                    button.setBackgroundColor(Color.parseColor("#4D7BFE"));
                } else {
                    button.setBackgroundColor(Color.parseColor("#DDDDDD"));
                }
            }
        } else {
            editText1.setText("≈***CNY");
            button.setBackgroundColor(Color.parseColor("#DDDDDD"));
        }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}