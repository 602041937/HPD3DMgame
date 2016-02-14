package com.hpdxay.hpd3dmgame.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hpdxay.hpd3dmgame.R;
import com.hpdxay.hpd3dmgame.entities.ChapterCommentListItemDataItem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hpd on 2016/1/28.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private Context context;
    private List<ChapterCommentListItemDataItem> list;

    public CommentAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_chapter_comment, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {

        ChapterCommentListItemDataItem item = list.get(position);
        String username = item.getUsername();
        if (username.equals("????")) {
            holder.userName.setText("匿名用户");
        } else {
            holder.userName.setText(username);
        }
        String msg = item.getMsg();
        holder.userContent.setText(msg);

    }

    public void addAll(Collection<? extends ChapterCommentListItemDataItem> newList) {
        int position = list.size();
        list.addAll(newList);
        notifyItemRangeInserted(position, newList.size());
    }

    public void clear() {
        int size = list.size();
        list.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView userIcon;
        private TextView userName;
        private TextView userContent;

        public CommentViewHolder(View itemView) {
            super(itemView);
            userIcon = (SimpleDraweeView) itemView.findViewById(R.id.id_item_chapter_comment_icon);
            userName = (TextView) itemView.findViewById(R.id.id_item_chapter_comment_username);
            userContent = (TextView) itemView.findViewById(R.id.id_item_chapter_comment_content);
        }
    }
}
