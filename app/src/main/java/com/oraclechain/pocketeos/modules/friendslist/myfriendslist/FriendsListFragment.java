package com.oraclechain.pocketeos.modules.friendslist.myfriendslist;


import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.base.BaseFragment;
import com.oraclechain.pocketeos.bean.User;
import com.oraclechain.pocketeos.modules.seach.SeachActivity;
import com.oraclechain.pocketeos.view.contact.SideBarView;
import com.oraclechain.pocketeos.view.contact.UserAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 富豪榜
 */
public class FriendsListFragment extends BaseFragment<FriendsListView, FriendsListPresenter> implements FriendsListView, SideBarView.LetterSelectListener {
    @BindView(R.id.listview)
    ListView mListview;
    @BindView(R.id.sidebarview)
    SideBarView mSidebarview;
    @BindView(R.id.tip)
    TextView mTip;
    @BindView(R.id.go_seach)
    RelativeLayout mGoSeach;
    @BindView(R.id.title)
    TextView mTitle;
    Unbinder unbinder;


    private UserAdapter mAdapter;
    private ArrayList<User> mDataBeanArrayList = new ArrayList<>();

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_friends_list;
    }

    @Override
    protected void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
    }

    @Override
    public void initEvent() {
        mListview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE://不滚动
                        mTip.setVisibility(View.GONE);
                        break;
                    case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:// 滚动时
                        mTip.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mDataBeanArrayList.size() != 0) {
                    mTip.setText(mDataBeanArrayList.get(firstVisibleItem).getLetter());
                    mSidebarview.listselectposition(mDataBeanArrayList.get(firstVisibleItem).getLetter());
                }
            }
        });
        mGoSeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.next(getActivity(), SeachActivity.class);
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        ImmersionBar.setTitleBar(getActivity(), mTitle);
    }

    @Override
    public void onResume() {
        super.onResume();
        //重新请求关注列表
        presenter.getData();//获取关注好友列表
    }

    @Override
    public FriendsListPresenter initPresenter() {
        return new FriendsListPresenter(getActivity());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden && mImmersionBar != null) {
            mImmersionBar.statusBarDarkFont(true, 0.2f).init();
        }
    }

    @Override
    public void getDataHttp(ArrayList<User> dataBeanArrayList) {
        mDataBeanArrayList.clear();
        for (User user : dataBeanArrayList) {
            mDataBeanArrayList.add(user);
        }
        //设置数据
        mAdapter = new UserAdapter(getActivity());
        mAdapter.setData(mDataBeanArrayList);
        mListview.setAdapter(mAdapter);

        //设置回调
        mSidebarview.setOnLetterSelectListen(this);
    }

    @Override
    public void getDataHttpFail(String msg) {
        toast(msg);
    }

    @Override
    public void onLetterSelected(String letter) {
        setListviewPosition(letter);
        mTip.setText(letter);
        mTip.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLetterChanged(String letter) {
        setListviewPosition(letter);
        mTip.setText(letter);
    }

    @Override
    public void onLetterReleased(String letter) {
        mTip.setVisibility(View.GONE);
    }

    private void setListviewPosition(String letter) {
        int firstLetterPosition = mAdapter.getFirstLetterPosition(letter);
        if (firstLetterPosition != -1) {
            mListview.setSelection(firstLetterPosition);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}