package com.hpdxay.hpd3dmgame.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.hpdxay.hpd3dmgame.ChapterContentActivity;
import com.hpdxay.hpd3dmgame.GameContentActivity;
import com.hpdxay.hpd3dmgame.R;
import com.hpdxay.hpd3dmgame.entities.ChapterListItemDataItem;
import com.hpdxay.hpd3dmgame.entities.GameItemEntity;
import com.hpdxay.hpd3dmgame.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpd on 2016/1/27.
 */
public class GameItemAdapter extends RecyclerView.Adapter<GameItemAdapter.ItemViewHolder> {

    private Context context;
    private List<GameItemEntity> list;

    public GameItemAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_game, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final GameItemEntity item = list.get(position);
        //设置uri
        Uri uri = Uri.parse("http://www.3dmgame.com" + item.getLitpic());
        //根据uri创建请求图片的请求体
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        //根据请求体来获取控制器
        final AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(holder.mIcon.getController())
                .build();

        holder.mIcon.setController(controller);
        holder.mTitle.setText(item.getShorttitle());
//        holder.mTime.setText(DateUtil.stringToDate(item.getPubdate()));
//        holder.mCommentsCount.setText(item.getFeedback());
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GameContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("pic", "http://www.3dmgame.com" + item.getLitpic());
                bundle.putString("shorttitle", item.getShorttitle());
                bundle.putString("tid", item.getTid());
                bundle.putString("made_company", item.getMade_company());
                bundle.putString("release_date", item.getRelease_date());
                bundle.putString("release_company", item.getRelease_company());
                bundle.putString("websit", item.getWebsit());
                bundle.putString("terrace", item.getTerrace());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addAll(List<? extends GameItemEntity> newList) {
        int size = list.size();
        list.addAll(newList);
        int count = newList.size();
        notifyItemRangeInserted(size, count);
    }

    public void clear() {
        int size = list.size();
        list.clear();
        //notifyDataSetChanged();
        notifyItemRangeRemoved(0, size);
    }


    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private SimpleDraweeView mIcon;
        private TextView mTitle;
        private LinearLayout mLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mIcon = (SimpleDraweeView) itemView.findViewById(R.id.id_item_game_icon);
            mTitle = (TextView) itemView.findViewById(R.id.id_item_game_title);
            mLayout = (LinearLayout) itemView.findViewById(R.id.id_item_game_layout);

        }
    }
}
