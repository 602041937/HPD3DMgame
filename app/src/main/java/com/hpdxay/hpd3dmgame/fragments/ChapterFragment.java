package com.hpdxay.hpd3dmgame.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hpdxay.hpd3dmgame.R;
import com.hpdxay.hpd3dmgame.adapters.ChapterItemAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChapterFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<String> mtitles;
    private ChapterItemAdapter adapter;
    private List<String> typeids;


    public ChapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chapter, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        

        mTabLayout = (TabLayout) view.findViewById(R.id.id_chapter_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.id_chapter_pager);

        typeids = new ArrayList<>();
        typeids.add("1");
        typeids.add("2");
        typeids.add("151");
        typeids.add("152");
        typeids.add("153");
        typeids.add("154");
        typeids.add("196");
        typeids.add("197");
        typeids.add("199");
        typeids.add("25");

        //给tab添加标题
        mtitles = new ArrayList<String>();
        mtitles.add("文章首页");
        mtitles.add("新闻");
        mtitles.add("游戏杂谈");
        mtitles.add("硬件信息");
        mtitles.add("游戏前瞻");
        mtitles.add("游戏评测");
        mtitles.add("原创精品");
        mtitles.add("游戏盘点");
        mtitles.add("时事焦点");
        mtitles.add("攻略中心");

        mFragments = new ArrayList<>();
        for (int i = 0; i < mtitles.size(); i++) {
            ChapterItemFragment chapterItemFragment = new ChapterItemFragment();
            Bundle bundle = new Bundle();
            bundle.putString("typeid", typeids.get(i));
            chapterItemFragment.setArguments(bundle);
            mFragments.add(chapterItemFragment);
        }

        FragmentManager childFragmentManager = getChildFragmentManager();
        adapter = new ChapterItemAdapter(childFragmentManager, mFragments, mtitles);
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);


    }
}
