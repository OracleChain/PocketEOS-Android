package com.oraclechain.pocketeos.view.textwatcher;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by pocketEos on 2018/2/3.
 * 监听edittext文本框是否为空
 * 1.首位输入.的时候,补全为0.
 * <p>
 * 2.删除“.”后面超过4位后的数据
 * <p>
 * 3.如果起始位置为0,且第二位跟的不是".",则无法后续输入
 */

public class MakeRedPacketNumberTextWatcher implements TextWatcher {
    private EditText editText;
    private Button button;
    private EditText mRedPacketNymber;

    public MakeRedPacketNumberTextWatcher(EditText et, EditText redpacketNumber, Button btn) {
        editText = et;
        button = btn;
        mRedPacketNymber = redpacketNumber;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (!TextUtils.isEmpty(s.toString().trim()) && !TextUtils.isEmpty(mRedPacketNymber.getText().toString().trim())) {
            button.setBackgroundColor(Color.parseColor("#D82919"));
        } else {
            button.setBackgroundColor(Color.parseColor("#B3D82919"));
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

}