package com.hpdxay.hpd3dmgame.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.hpdxay.hpd3dmgame.R;
import com.hpdxay.hpd3dmgame.adapters.ItemAdapter;
import com.hpdxay.hpd3dmgame.dao.ChapterCommentListItemDataItemDao;
import com.hpdxay.hpd3dmgame.dao.ChapterListItemDataItemDao;
import com.hpdxay.hpd3dmgame.entities.ChapterCommentListItemDataItem;
import com.hpdxay.hpd3dmgame.entities.ChapterListItem;
import com.hpdxay.hpd3dmgame.entities.ChapterListItemDataItem;
import com.hpdxay.hpd3dmgame.utils.DBUtil;
import com.hpdxay.hpd3dmgame.utils.NetworkUtil;
import com.hpdxay.hpd3dmgame.widgets.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterItemFragment extends Fragment implements Callback<ChapterListItem>, PullToRefreshBase.OnRefreshListener2<RecyclerView> {


    private PullToRefreshRecyclerView mRecyclerView;
    private ItemAdapter adapter;
    private NetworkUtil.Service service;
    private Call<ChapterListItem> call;
    private int page = 1;
    private String typeid;


    public ChapterItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        typeid = bundle.getString("typeid");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = (PullToRefreshRecyclerView) view.findViewById(R.id.id_chapter_item_recycler);
        mRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        mRecyclerView.setOnRefreshListener(this);
        adapter = new ItemAdapter(getContext());
        mRecyclerView.setAdapter(adapter);
        List<ChapterListItemDataItem> items = null;
        if (typeid.equals("1")) {
            items = DBUtil.getSession().getChapterListItemDataItemDao().loadAll();
        } else {
            items = DBUtil.getSession().
                    getChapterListItemDataItemDao().queryBuilder().
                    where(ChapterListItemDataItemDao.Properties.Typeid.eq(typeid)).list();
        }
        adapter.addAll(items);
        Retrofit build = new Retrofit.Builder().
                baseUrl("http://www.3dmgame.com").
                addConverterFactory(GsonConverterFactory.create()).build();
        service = build.create(NetworkUtil.Service.class);

    }

    public void loadFromNet() {

        Retrofit build = new Retrofit.Builder().
                baseUrl("http://www.3dmgame.com").
                addConverterFactory(GsonConverterFactory.create()).build();
        service = build.create(NetworkUtil.Service.class);
        call = service.getChapterListItem(10, typeid, page);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<ChapterListItem> response) {
        ChapterListItem body = response.body();
        if (body != null) {
            Map<Integer, ChapterListItemDataItem> data = body.getData();
            final ArrayList<ChapterListItemDataItem> items = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                ChapterListItemDataItem item = data.get(i);
                items.add(item);
            }
            adapter.addAll(items);
            //存数据
            new Thread(new Runnable() {
                @Override
                public void run() {
                    DBUtil.getSession().getChapterListItemDataItemDao().insertOrReplaceInTx(items);
                }
            }).start();

        }
    }

    @Override
    public void onFailure(Throwable t) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        adapter.clear();
        call = service.getChapterListItem(10, typeid, 1);
        call.enqueue(this);
        page = 1;
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.onRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        call = service.getChapterListItem(10, typeid, ++page);
        call.enqueue(this);

        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.onRefreshComplete();
            }
        }, 2000);
    }
}
