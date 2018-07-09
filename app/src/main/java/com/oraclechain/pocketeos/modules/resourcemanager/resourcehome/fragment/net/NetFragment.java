package com.oraclechain.pocketeos.modules.resourcemanager.resourcehome.fragment.net;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.BlockChainAccountInfoBean;
import com.oraclechain.pocketeos.modules.resourcemanager.changenet.ChangeNetActivity;
import com.oraclechain.pocketeos.utils.BigDecimalUtil;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 宽带管理
 */
public class NetFragment extends BaseFragment<NetView, NetPresenter> implements NetView {

    @BindView(R.id.cpu_change_limit)
    TextView mCpuChangeLimit;
    @BindView(R.id.cpu_total_quota)
    TextView mCpuTotalQuota;
    @BindView(R.id.cpu_quota_staked)
    TextView mCpuQuotaStaked;
    @BindView(R.id.cpu_now_take_up)
    TextView mCpuNowTakeUp;
    @BindView(R.id.cpu_now_usable)
    TextView mCpuNowUsable;
    @BindView(R.id.net_change_limit)
    TextView mNetChangeLimit;
    @BindView(R.id.net_total_quota)
    TextView mNetTotalQuota;
    @BindView(R.id.net_quota_staked)
    TextView mNetQuotaStaked;
    @BindView(R.id.net_now_take_up)
    TextView mNetNowTakeUp;
    @BindView(R.id.net_now_usable)
    TextView mNetNowUsable;
    @BindView(R.id.total_stake)
    TextView mTotalStake;

    BlockChainAccountInfoBean.DataBean mBlockChainAccountInfoBean;
    BigDecimal cpu, net;

    @OnClick({R.id.cpu_change_limit, R.id.net_change_limit})
    public void onViewClicked(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.cpu_change_limit:
                bundle.putString("title", getString(R.string.change_stake_number));
                bundle.putString("tag", "1");
                bundle.putString("amount", cpu.toString());
                bundle.putString("account", mBlockChainAccountInfoBean.getAccount_name());
                bundle.putString("price", BigDecimalUtil.divide(new BigDecimal(mBlockChainAccountInfoBean.getTotal_resources().getCpu_weight().substring(0, (mBlockChainAccountInfoBean.getTotal_resources().getCpu_weight().length() - 4))), new BigDecimal(mBlockChainAccountInfoBean.getCpu_limit().getMax()), 8).toString());
                ActivityUtils.next(getActivity(), ChangeNetActivity.class, bundle);
                break;
            case R.id.net_change_limit:
                bundle.putString("title", getString(R.string.change_stake_number));
                bundle.putString("tag", "2");
                bundle.putString("amount", net.toString());
                bundle.putString("account", mBlockChainAccountInfoBean.getAccount_name());
                bundle.putString("price", BigDecimalUtil.divide(new BigDecimal(mBlockChainAccountInfoBean.getTotal_resources().getNet_weight().substring(0, (mBlockChainAccountInfoBean.getTotal_resources().getNet_weight().length() - 4))), new BigDecimal(mBlockChainAccountInfoBean.getNet_limit().getMax()), 8).toString());
                ActivityUtils.next(getActivity(), ChangeNetActivity.class, bundle);
                break;
        }
    }

    @Override
    public void getBlockchainAccountInfoDataHttp(BlockChainAccountInfoBean.DataBean blockChainAccountInfoBean) {
        mBlockChainAccountInfoBean = blockChainAccountInfoBean;
        mCpuTotalQuota.setText(getString(R.string.total_quota) + mBlockChainAccountInfoBean.getCpu_limit().getMax() + " ms");
        mCpuQuotaStaked.setText(getString(R.string.quota_staked) + mBlockChainAccountInfoBean.getTotal_resources().getCpu_weight());
        mCpuNowTakeUp.setText(getString(R.string.now_take_up) + mBlockChainAccountInfoBean.getCpu_limit().getUsed() + " ms");
        mCpuNowUsable.setText(getString(R.string.now_usable) + mBlockChainAccountInfoBean.getCpu_limit().getAvailable() + " ms");

        mNetTotalQuota.setText(getString(R.string.total_quota)+ mBlockChainAccountInfoBean.getNet_limit().getMax() + " bytes");
        mNetQuotaStaked.setText(getString(R.string.quota_staked) + mBlockChainAccountInfoBean.getTotal_resources().getNet_weight());
        mNetNowTakeUp.setText(getString(R.string.now_take_up)+ mBlockChainAccountInfoBean.getNet_limit().getUsed() + " bytes");
        mNetNowUsable.setText(getString(R.string.now_usable)+ mBlockChainAccountInfoBean.getNet_limit().getAvailable() + " bytes");

        cpu = new BigDecimal(mBlockChainAccountInfoBean.getTotal_resources().getCpu_weight().substring(0, (mBlockChainAccountInfoBean.getTotal_resources().getCpu_weight().length() - 4)));
        net = new BigDecimal(mBlockChainAccountInfoBean.getTotal_resources().getNet_weight().substring(0, (mBlockChainAccountInfoBean.getTotal_resources().getNet_weight().length() - 4)));
        mTotalStake.setText(BigDecimalUtil.add(cpu, net, 4) + "");
    }

    @Override
    public void getDataHttpFail(String msg) {
        toast(msg);
    }

    @Override
    public void onResume() {
        super.onResume();
        mBlockChainAccountInfoBean = new BlockChainAccountInfoBean.DataBean();
        presenter.getAccountInfoData(getArguments().getString("account"));
    }

    @Override
    public NetPresenter initPresenter() {
        return new NetPresenter(getActivity());
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
        return R.layout.fragment_net;
    }
}
