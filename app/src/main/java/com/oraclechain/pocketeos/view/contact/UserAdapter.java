package com.oraclechain.pocketeos.view.contact;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oraclechain.pocketeos.R;
import com.oraclechain.pocketeos.app.ActivityUtils;
import com.oraclechain.pocketeos.bean.User;
import com.oraclechain.pocketeos.modules.friendslist.friendsdetails.FriendsDetailsActivity;
import com.oraclechain.pocketeos.modules.friendslist.pelist.PelistActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketEos on 2017/11/30.
 */
public class UserAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<User> users;

    public UserAdapter(Context context) {
        this.mContext = context;
        users = new ArrayList<>();
    }

    public void setData(List<User> data) {
        this.users.clear();
        this.users.addAll(data);
    }


    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_friend_list, null);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.title);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.name);
            viewHolder.tvItem = (LinearLayout) convertView.findViewById(R.id.item);
            viewHolder.friends_img = (ImageView) convertView.findViewById(R.id.friends_img);
            viewHolder.tvAllMoney = (TextView) convertView.findViewById(R.id.all_money);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvAllMoney.setVisibility(View.GONE);
        viewHolder.tvName.setText(users.get(position).getName());
        if (users.get(position).getLetter().equals("@")) {
            if (position == 0) {
                viewHolder.friends_img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.friends_top));
            } else if (position == 1) {
                viewHolder.friends_img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.friends_group));
            }
        } else if (!users.get(position).getLetter().equals("@")) {
            viewHolder.friends_img.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.ic_launcher));
        }
        viewHolder.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                if (position == 0) {
                    bundle.putString("type", "0");
                    ActivityUtils.next((Activity) mContext, PelistActivity.class, bundle);
                } else if (position == 1) {
                    bundle.putString("type", "1");
                    ActivityUtils.next((Activity) mContext, PelistActivity.class, bundle);
                } else {
                    bundle.putString("name", users.get(position).getName());
                    bundle.putString("friend_type", users.get(position).getFriend_type());
                    bundle.putString("avatar", users.get(position).getAvatar());
                    bundle.putString("uid", users.get(position).getUid());
                    bundle.putString("from", "friend");
                    ActivityUtils.next((Activity) mContext, FriendsDetailsActivity.class, bundle);
                }
            }
        });
        //当前的item的title与上一个item的title不同的时候回显示title(A,B,C......)
        if (position == getFirstLetterPosition(position) && !users.get(position).getLetter().equals("@")) {
            viewHolder.tvTitle.setVisibility(View.VISIBLE);
            viewHolder.tvTitle.setText(users.get(position).getLetter().toUpperCase());
        } else {
            viewHolder.tvTitle.setVisibility(View.GONE);
        }
        return convertView;
    }

    /**
     * 顺序遍历所有元素．找到position对应的title是什么（A,B,C?）然后找这个title下的第一个item对应的position
     *
     * @param position
     * @return
     */
    private int getFirstLetterPosition(int position) {

        String letter = users.get(position).getLetter();
        int cnAscii = ChineseToEnglish.getCnAscii(letter.toUpperCase().charAt(0));
        int size = users.size();
        for (int i = 0; i < size; i++) {
            if (cnAscii == users.get(i).getLetter().charAt(0)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 顺序遍历所有元素．找到letter下的第一个item对应的position
     *
     * @param letter
     * @return
     */
    public int getFirstLetterPosition(String letter) {
        int size = users.size();
        for (int i = 0; i < size; i++) {
            if (letter.charAt(0) == users.get(i).getLetter().charAt(0)) {
                return i;
            }
        }
        return -1;
    }

    static   class ViewHolder {
        TextView tvName;
        TextView tvTitle;
        TextView tvAllMoney;
        ImageView friends_img;
        LinearLayout tvItem;
    }

}
