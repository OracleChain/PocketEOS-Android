package com.oraclechain.pocketeos.modules.leftdrawer.transactionhistory;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.CommonAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.MultiItemTypeAdapter;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.app.MyApplication;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.AccountInfoBean;
import com.oraclechain.pocketeos.bean.PostChainHistoryBean;
import com.oraclechain.pocketeos.bean.TransferHistoryBean;
import com.oraclechain.pocketeos.utils.JsonUtil;
import com.oraclechain.pocketeos.utils.RotateUtils;
import com.oraclechain.pocketeos.utils.ShowDialog;
import com.oraclechain.pocketeos.view.RecycleViewDivider;
import com.oraclechain.pocketeos.view.popupwindow.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

public class TransactionHistoryActivity extends BaseAcitvity<TransactionHistoryView, TransactionHistoryPresenter> implements TransactionHistoryView {


    @BindView(R.id.recycle_transferaccounts_history)
    XRecyclerView mRecycleTransferaccountsHistory;
    @BindView(R.id.switch_number)
    TextView mSwitchNumber;
    @BindView(R.id.look_number)
    ImageView mLookNumber;


    Boolean isSHow = false;
    BasePopupWindow basePopupWindow;
    private List<AccountInfoBean> mAccountInfoBeanList = new ArrayList<>();

    private List<TransferHistoryBean.DataBeanX.TransactionsBean> mDataBeanList = new ArrayList<>();//交易历史
    private EmptyWrapper mHistoryAdapter;
    private int size = 10; //每页加载的数量

    private PostChainHistoryBean mPostChainHistoryBean = new PostChainHistoryBean();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transaction_history;
    }

    @Override
    public TransactionHistoryPresenter initPresenter() {
        return new TransactionHistoryPresenter(TransactionHistoryActivity.this);
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.transaction_history));

        mAccountInfoBeanList = JsonUtil.parseJsonToArrayList(MyApplication.getInstance().getUserBean().getAccount_info(), AccountInfoBean.class);
        mSwitchNumber.setText(mAccountInfoBeanList.get(0).getAccount_name());

        LinearLayoutManager layoutManager = new LinearLayoutManager(TransactionHistoryActivity.this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycleTransferaccountsHistory.setLayoutManager(layoutManager);
        mRecycleTransferaccountsHistory.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));
        mRecycleTransferaccountsHistory.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        mRecycleTransferaccountsHistory.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecycleTransferaccountsHistory.setLoadingMoreEnabled(true);
        mRecycleTransferaccountsHistory.setPullRefreshEnabled(false);
        mRecycleTransferaccountsHistory.setArrowImageView(R.drawable.arrow);
        mRecycleTransferaccountsHistory.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                mPostChainHistoryBean.setSkip_seq(mDataBeanList.size());
                presenter.getTransferHistoryData(mPostChainHistoryBean);
            }
        });
    }

    @Override
    protected void initData() {
        showProgress();
        mPostChainHistoryBean.setAccount_name(mSwitchNumber.getText().toString());
        mPostChainHistoryBean.setSkip_seq(0);
        mPostChainHistoryBean.setNum_seq(size);
        presenter.getTransferHistoryData(mPostChainHistoryBean);
        mHistoryAdapter = new EmptyWrapper(AdapterManger.getCoinDetailsHistoryAdapter(this, mDataBeanList, mSwitchNumber.getText().toString().trim()));
        mHistoryAdapter.setEmptyView(R.layout.empty_project);
        mRecycleTransferaccountsHistory.setAdapter(mHistoryAdapter);

    }

    @Override
    public void initEvent() {
        mSwitchNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSHow = !isSHow;
                RotateUtils.rotateArrow(mLookNumber, isSHow);
                if (basePopupWindow != null && basePopupWindow.isShowing()) {
                    basePopupWindow.dismiss();
                } else {
                    basePopupWindow = new BasePopupWindow.Builder(TransactionHistoryActivity.this).
                            setView(LayoutInflater.from(TransactionHistoryActivity.this).inflate(R.layout.popuwindow_exchange_type, null))
                            .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                            .setOutsideTouchable(false)
                            .setAnimationStyle(R.style.AnimDown)
                            .create();
                    basePopupWindow.showAsDropDown(mSwitchNumber);
                    isSHow = basePopupWindow.setAccountData(TransactionHistoryActivity.this, mAccountInfoBeanList, mSwitchNumber.getText().toString().toString().trim(), mLookNumber, isSHow);
                    CommonAdapter commonAdapter = (CommonAdapter) ((RecyclerView) basePopupWindow.getContentView().findViewById(R.id.exchange_two_type)).getAdapter();
                    commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                            basePopupWindow.dismiss();
                            mSwitchNumber.setText(mAccountInfoBeanList.get(position).getAccount_name());
                            ShowDialog.showDialog(TransactionHistoryActivity.this, "", true, null).show();
                            mPostChainHistoryBean.setAccount_name(mSwitchNumber.getText().toString());
                            mPostChainHistoryBean.setSkip_seq(0);
                            mPostChainHistoryBean.setNum_seq(size);
                            mDataBeanList.clear();
                            presenter.getTransferHistoryData(mPostChainHistoryBean);
                            isSHow = !isSHow;
                            RotateUtils.rotateArrow(mLookNumber, isSHow);
                        }

                        @Override
                        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                            return false;
                        }
                    });
                }
            }
        });
    }

    @Override
    public void getTransferHistoryDataHttp(TransferHistoryBean.DataBeanX transferHistoryBean) {
        hideProgress();
        mRecycleTransferaccountsHistory.loadMoreComplete();
        for (int i = 0; i < transferHistoryBean.getTransactions().size(); i++) {
            if (transferHistoryBean.getTransactions().get(i).getTransaction().getTransaction().getActions().get(0).getName().equals("transfer")) {
                TransferHistoryBean.DataBeanX.TransactionsBean itemdata = transferHistoryBean.getTransactions().get(i);
                mDataBeanList.add(itemdata);
            }
        }

        mHistoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        hideProgress();
        mRecycleTransferaccountsHistory.loadMoreComplete();
        toast(msg);
    }

}
