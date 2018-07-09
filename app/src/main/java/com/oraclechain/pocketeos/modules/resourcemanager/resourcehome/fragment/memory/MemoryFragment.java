package com.oraclechain.pocketeos.modules.resourcemanager.resourcehome.fragment.memory;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.BlockChainAccountInfoBean;
import com.oraclechain.pocketeos.modules.resourcemanager.changememory.ChangeMemoryActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 存储管理
 */
public class MemoryFragment extends BaseFragment<MemoryView, MemoryPresenter> implements MemoryView {

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.memory_quota_details)
    TextView mMemoryQuotaDetails;
    @BindView(R.id.buy_quota)
    TextView mBuyQuota;
    @BindView(R.id.sale_quota)
    TextView mSaleQuota;

    BlockChainAccountInfoBean.DataBean mBlockChainAccountInfoBean;

    @OnClick({R.id.buy_quota, R.id.sale_quota})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.buy_quota:
                bundle.putString("title", getString(R.string.buy_quota));
                bundle.putString("tag", "1");
                bundle.putString("totalRam", mBlockChainAccountInfoBean.getTotal_resources().getRam_bytes());
                bundle.putString("account", mBlockChainAccountInfoBean.getAccount_name());
                ActivityUtils.next(getActivity(), ChangeMemoryActivity.class, bundle);
                break;
            case R.id.sale_quota:
                bundle.putString("title", getString(R.string.sale_quota));
                bundle.putString("tag", "2");
                bundle.putString("totalRam", mBlockChainAccountInfoBean.getTotal_resources().getRam_bytes());
                bundle.putString("account", mBlockChainAccountInfoBean.getAccount_name());
                ActivityUtils.next(getActivity(), ChangeMemoryActivity.class, bundle);
                break;
        }
    }

    @Override
    public void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean) {
        mBlockChainAccountInfoBean = blockChainAccountInfoBean;
        mMemoryQuotaDetails.setText(getString(R.string.ram_quota_details) + mBlockChainAccountInfoBean.getRam_usage() + "bytes/" + mBlockChainAccountInfoBean.getTotal_resources().getRam_bytes() + "bytes)");
        BigDecimal ram_usage = new BigDecimal(mBlockChainAccountInfoBean.getRam_usage());
        BigDecimal ram_quota = new BigDecimal(mBlockChainAccountInfoBean.getRam_quota());
        mProgressBar.setProgress(BigDecimalUtil.multiply(BigDecimalUtil.divide(ram_usage, ram_quota, 4), new BigDecimal(100), 0).intValue());
    }

    @Override
    public void getDataHttpFail(String msg) {

    }

    @Override
    public void onResume() {
        super.onResume();
        mBlockChainAccountInfoBean = new BlockChainAccountInfoBean.DataBean();
        presenter.getAccountInfoData(getArguments().getString("account"));
    }

    @Override
    public MemoryPresenter initPresenter() {
        return new MemoryPresenter(getActivity());
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_memory;
    }
}
