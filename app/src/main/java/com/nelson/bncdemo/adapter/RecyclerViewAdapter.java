package com.nelson.bncdemo.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nelson.bncdemo.R;
import com.nelson.bncdemo.UserDetailActivity;
import com.nelson.bncdemo.UserDetailFragment;
import com.nelson.bncdemo.UserListActivity;
import com.nelson.bncdemo.data.local.model.User;
import com.nelson.bncdemo.dummy.DummyContent;

import java.util.List;

/**
 * Created by Nelson on 8/4/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //private final List<DummyContent.DummyItem> mValues;
    private List<User> mUsers;
    private boolean mTwoPane;
    private Context mContext;
    public RecyclerViewAdapter(List<User> users, Boolean isTwoPane, Context context) {
//        mValues = items;
        mTwoPane = isTwoPane;
        mContext = context;
        mUsers = users;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_content, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.user = mUsers.get(position);
        holder.mIdView.setText(mUsers.get(position).getId());
        holder.mContentView.setText(mUsers.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(UserDetailFragment.ARG_ITEM_ID, String.valueOf(holder.user.getId()));
                    UserDetailFragment fragment = new UserDetailFragment();
                    fragment.setArguments(arguments);
                    ((AppCompatActivity)mContext).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.user_detail_container, fragment)
                            .commit();
                } else {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, UserDetailActivity.class);
                    intent.putExtra(UserDetailFragment.ARG_ITEM_ID, holder.user.getId());

                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
      //  public DummyContent.DummyItem mItem;
        public User user;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
