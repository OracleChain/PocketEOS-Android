package com.oraclechain.pocketeos.modules.transaction.transferaccounts.switchfriend;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.adapter.AdapterManger;
import com.oraclechain.pocketeos.adapter.baseadapter.wrapper.EmptyWrapper;
import com.oraclechain.pocketeos.base.BaseAcitvity;
import com.oraclechain.pocketeos.bean.FriendsListInfoBean;
import com.oraclechain.pocketeos.view.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.oraclechain.pocketeos.utils.Utils.getContext;

public class SwitchFriendActivity extends BaseAcitvity<SwitchFriendView, SwitchFriendPresenter> implements SwitchFriendView {

    @BindView(R.id.recycle)
    RecyclerView mRecycle;
    private List<FriendsListInfoBean> mDataBeanList = new ArrayList<>();
    private EmptyWrapper mCommonAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_switch_friend;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {
        setCenterTitle(getString(R.string.friend_account));
    }

    @Override
    protected void initData() {
        presenter.getData();//获取关注好友账号列表

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecycle.setLayoutManager(layoutManager);
        mRecycle.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.HORIZONTAL, 1, getResources().getColor(R.color.line)));

        mCommonAdapter = new EmptyWrapper(AdapterManger.getFriendListAdapter(this, mDataBeanList, getIntent().getStringExtra("account")));
        mCommonAdapter.setEmptyView(R.layout.empty_project);
        mRecycle.setAdapter(mCommonAdapter);
    }

    @Override
    public void initEvent() {

    }

    @Override
    public SwitchFriendPresenter initPresenter() {
        return new SwitchFriendPresenter(SwitchFriendActivity.this);
    }

    @Override
    public void getDataHttp(List<FriendsListInfoBean> dataBeanList) {
        for (int i = 0; i < dataBeanList.size(); i++) {
            if (dataBeanList.get(i).getFollowType().equals("2")) {
                mDataBeanList.add(dataBeanList.get(i));
            }
        }
        mCommonAdapter.notifyDataSetChanged();
    }

    @Override
    public void getDataHttpFail(String msg) {
        toast(msg);
    }
}
