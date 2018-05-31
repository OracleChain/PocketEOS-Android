package com.oraclechain.pocketeos.modules.dapp.paidanswer.makequestion;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.google.gson.Gson;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.base.ViewHolder;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AnswerBean;
import com.oraclechain.pocketeos.bean.ApproveMessageBean;
import com.oraclechain.pocketeos.bean.AskMessageBean;
import com.oraclechain.pocketeos.bean.ChainAsktitleBean;
import com.oraclechain.pocketeos.bean.ChainOptionanswersBean;
import com.oraclechain.pocketeos.blockchain.AskansDatamanger;
import com.oraclechain.pocketeos.modules.normalvp.NormalPresenter;
import com.oraclechain.pocketeos.modules.normalvp.NormalView;
import com.oraclechain.pocketeos.utils.AndroidBug5497Workaround;
import com.oraclechain.pocketeos.utils.DateUtils;
import com.oraclechain.pocketeos.utils.KeyBoardUtil;
import com.oraclechain.pocketeos.utils.PasswordToKeyUtils;
import com.oraclechain.pocketeos.utils.StringUtils;
import com.oraclechain.pocketeos.utils.ToastUtils;
import com.oraclechain.pocketeos.view.ClearEditText;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.dialog.confimdialog.Callback;
import com.oraclechain.pocketeos.view.dialog.confimdialog.ConfirmDialog;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordCallback;
import com.oraclechain.pocketeos.view.dialog.passworddialog.PasswordDialog;
import com.oraclechain.pocketeos.view.textwatcher.FourMoneyTextWatcher;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

//提问页面
public class MakeQuestionActivity extends BaseAcitvity<NormalView, NormalPresenter> implements NormalView {


    public static String[] option = {"C", "D", "E", "F"};//最多6个选项
    public static String[] alloption = {"A", "B", "C", "D", "E", "F"};//最多6个选项
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.tv_right_text)
    TextView mTvRightText;
    @BindView(R.id.property_number)
    ClearEditText mPropertyNumber;
    @BindView(R.id.end_time)
    TextView mEndTime;
    @BindView(R.id.input_question_title)
    ClearEditText mInputQuestionTitle;
    @BindView(R.id.input_question_detail)
    ClearEditText mInputQuestionDetail;
    @BindView(R.id.recycle_question_answer)
    RecyclerView mRecycleQuestionAnswer;
    @BindView(R.id.add_answer_button)
    Button mAddAnswerButton;
    List<AnswerBean> mAnswerBeanList = new ArrayList<>();//问题答案选项
    long dateTime = 0;//合约结束时间
    private TimePickerView mTimePickerView;
    private CommonAdapter mCommonAdapter;
    private Map<Integer, String> mAnswerBeanMap = new HashMap<>();

    private String userPassword = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_make_question;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        AndroidBug5497Workaround.assistActivity(activity);

        setCenterTitle(getString(R.string.let_me_make_question));
        setRightTitle(getString(R.string.question_commit), true);

        mPropertyNumber.addTextChangedListener(new FourMoneyTextWatcher(mPropertyNumber));//限制金额最多为小数点后面四位

        LinearLayoutManager layoutManager = new LinearLayoutManager(MakeQuestionActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleQuestionAnswer.setLayoutManager(layoutManager);
        mRecycleQuestionAnswer.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mCommonAdapter = new CommonAdapter<AnswerBean>(MakeQuestionActivity.this, R.layout.item_question_answer, mAnswerBeanList) {
            @Override
            protected void convert(final ViewHolder holder, AnswerBean answerBean, final int position) {
                holder.setText(R.id.answer_option, answerBean.getAnswer_option() + ".");
                ClearEditText clearEditText = (ClearEditText) holder.getView(R.id.answer_details);
                clearEditText.setText(answerBean.getAnswer_details());
                ImageView imageView = holder.getView(R.id.delet);
                if (position == 0 || position == 1) {
                    imageView.setVisibility(View.GONE);
                } else {
                    imageView.setVisibility(View.VISIBLE);
                }

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mAnswerBeanList.remove(position);
                        mAnswerBeanMap.remove(position);
                        mCommonAdapter.notifyItemRemoved(position);
                        updata();
                    }
                });

                clearEditText.addTextChangedListener(new MakeQuestionTextWatcher(position));
            }
        };
        mRecycleQuestionAnswer.setAdapter(mCommonAdapter);
    }

    @Override
    protected void initData() {
        AnswerBean answerBean = new AnswerBean();
        answerBean.setAnswer_option("A");
        answerBean.setAnswer_details("");
        mAnswerBeanList.add(answerBean);
        AnswerBean answerBean1 = new AnswerBean();
        answerBean1.setAnswer_option("B");
        answerBean1.setAnswer_details("");
        mAnswerBeanList.add(answerBean1);
    }

    @Override
    public void initEvent() {
        mTvRightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPropertyNumber.getText().toString().length() != 0 && mEndTime.getText().toString().length() != 0 && mInputQuestionTitle.getText().toString().length() != 0 && mInputQuestionDetail.getText().toString().length() != 0 && mAnswerBeanList.size() >= 2) {
                    ConfirmDialog confirmDialog = new ConfirmDialog(MakeQuestionActivity.this, new Callback() {
                        @Override
                        public void callback() {
                            PasswordDialog mPasswordDialog = new PasswordDialog(MakeQuestionActivity.this, new PasswordCallback() {
                                @Override
                                public void sure(final String password) {
                                    if (MyApplication.getInstance().getUserBean().getWallet_shapwd().equals(PasswordToKeyUtils.shaCheck(password))) {
                                        userPassword = password;
                                        showProgress();

                                        new AskansDatamanger(MakeQuestionActivity.this, userPassword, new AskansDatamanger.Callback() {
                                            @Override
                                            public void isApprove(String action) {
                                                if (action.equals(AskansDatamanger.ACTIONAPPROVE)) {
                                                    new AskansDatamanger(MakeQuestionActivity.this, userPassword, new AskansDatamanger.Callback() {
                                                        @Override
                                                        public void isApprove(String action) {
                                                            if (action.equals(AskansDatamanger.ACTIONASK)) {
                                                                toast(getString(R.string.make_question_success));
                                                                EventBus.getDefault().post("ok");
                                                                finish();
                                                            }
                                                        }
                                                    }).pushAction(AskansDatamanger.OCTASKANSCONTRACT, AskansDatamanger.ACTIONASK,
                                                            new Gson().toJson(new AskMessageBean("1", getIntent().getStringExtra("account"), StringUtils.addZero(mPropertyNumber.getText().toString().trim())+" OCT", "0", dateTime + "",
                                                                    mAnswerBeanList.size() + "",
                                                                    new Gson().toJson(new ChainAsktitleBean(mInputQuestionTitle.getText().toString(), mInputQuestionDetail.getText().toString())),
                                                                    new Gson().toJson(new ChainOptionanswersBean(mAnswerBeanMap.get(0), mAnswerBeanMap.get(1), mAnswerBeanMap.get(2),
                                                                            mAnswerBeanMap.get(3), mAnswerBeanMap.get(4), mAnswerBeanMap.get(5))))),
                                                            getIntent().getStringExtra("account"));
                                                }
                                            }
                                        }).pushAction(AskansDatamanger.OCTCONTRACT, AskansDatamanger.ACTIONAPPROVE, new Gson().toJson(new ApproveMessageBean(getIntent().getStringExtra("account"), AskansDatamanger.OCTASKANSCONTRACT, StringUtils.addZero(mPropertyNumber.getText().toString().trim())+" OCT")), getIntent().getStringExtra("account"));
                                    } else {
                                        toast(getResources().getString(R.string.password_error));
                                    }
                                }

                                @Override
                                public void cancle() {
                                }
                            });
                            mPasswordDialog.setCancelable(true);
                            mPasswordDialog.show();
                        }
                    });
                    confirmDialog.setCancelable(true);
                    confirmDialog.setContent(getString(R.string.make_question_toast) + mPropertyNumber.getText().toString() + " OCT");
                    confirmDialog.show();
                } else {
                    toast(getString(R.string.input_all_message));
                }
            }
        });
        mEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (KeyBoardUtil.isSoftInputShow(MakeQuestionActivity.this)) {
                    KeyBoardUtil.getInstance(MakeQuestionActivity.this).hide();
                }
                if (mTimePickerView == null) {
                    mTimePickerView = new TimePickerView.Builder(MakeQuestionActivity.this, new TimePickerView.OnTimeSelectListener() {
                        @Override
                        public void onTimeSelect(Date date, View v) {//选中事件回调
                            if (DateUtils.dateToStamp(DateUtils.getTime(date).toString()) - System.currentTimeMillis() > 0) {
                                mEndTime.setText(DateUtils.getTime(date).toString());
                                dateTime = (DateUtils.dateToStamp(mEndTime.getText().toString()) - System.currentTimeMillis()) / 1000;
                            } else {
                                toast(getString(R.string.choose_data_error));
                            }
                        }
                    }).setSubmitText(getString(R.string.sure))//确定按钮文字
                            .setCancelText(getString(R.string.seach_cancel))//取消按钮文字
//                            .setSubCalSize(DensityUtil.dip2px(MakeQuestionActivity.this, 10))//确定和取消文字大小
                            .setSubmitColor(R.color.theme_color)//确定按钮文字颜色
                            .setCancelColor(R.color.theme_color)//取消按钮文字颜色
                            .setTitleColor(R.color.theme_color)
                            .setType(new boolean[]{true, true, true, true, true, true})//年月日时分秒 的显示与否，不设置则默认全部显示
                            .setLabel("", "", "", "", "", "")//默认设置为年月日时分秒
                            .build();

                    mTimePickerView.show();
                } else {
                    mTimePickerView.show();
                }

            }
        });
    }

    @Override
    public NormalPresenter initPresenter() {
        return new NormalPresenter();
    }

    public void updata() {
        for (int j = 0; j < mAnswerBeanList.size(); j++) {
            mAnswerBeanList.get(j).setAnswer_option(alloption[j]);
            mAnswerBeanList.get(j).setAnswer_details(mAnswerBeanMap.get(j));
            mCommonAdapter.notifyDataSetChanged();
        }
    }


    @OnClick(R.id.add_answer_button)
    public void onViewClicked() {
        if (mAnswerBeanList.size() < 6) {
            AnswerBean answerBean = new AnswerBean();
            answerBean.setAnswer_option(option[mAnswerBeanList.size() - 2]);
            mAnswerBeanList.add(answerBean);
            mCommonAdapter.notifyItemChanged(mAnswerBeanList.size() + 1);
        } else {
            ToastUtils.showShortToast(R.string.add_answer_toast);
        }
    }

    class MakeQuestionTextWatcher implements TextWatcher {
        private int postion;

        public MakeQuestionTextWatcher(int postion) {
            this.postion = postion;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            //用户输入完毕后，处理输入数据，
            if (s != null) {
                mAnswerBeanMap.put(postion, s.toString());
            }
        }
    }
}
