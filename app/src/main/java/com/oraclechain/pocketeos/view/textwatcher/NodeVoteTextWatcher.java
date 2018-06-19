package com.oraclechain.pocketeos.view.textwatcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

import com.oraclechain.pocketeos.utils.BigDecimalUtil;
import com.oraclechain.pocketeos.utils.ToastUtils;

/**
 * Created by pocketEos on 2018/2/27.
 */

public class NodeVoteTextWatcher implements TextWatcher {
    private EditText editText;
    private SeekBar mSeekBar;
    private String eosToatalAmount;


    public NodeVoteTextWatcher(EditText et, SeekBar seekBar, String eosToatalAmount) {
        editText = et;
        mSeekBar = seekBar;
        mSeekBar.setFocusable(true);
        mSeekBar.setFocusableInTouchMode(true);
        this.eosToatalAmount = eosToatalAmount;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (BigDecimalUtil.greaterThan(BigDecimalUtil.toBigDecimal(s.toString()), BigDecimalUtil.toBigDecimal(eosToatalAmount))) {
            ToastUtils.showLongToast("您最多可输入" + eosToatalAmount + "EOS");
            editText.setText(eosToatalAmount);
            editText.setSelection(eosToatalAmount.length()); //光标移到最后
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
       /* if (!TextUtils.isEmpty(s.toString())) {
            for (int i = 0; i < mAccountVoteHistoryBeans.size(); i++) {
                mAccountVoteHistoryBeans.get(i).setNumber(BigDecimalUtil.multiply(new BigDecimal(Double.parseDouble(s.toString()) * 10000), new BigDecimal(voteWeight), 4) + "");
            }
            mCommonAdapter.notifyDataSetChanged();
        }*/
    }


}