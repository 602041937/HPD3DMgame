package com.hpdxay.hpd3dmgame;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.hpdxay.hpd3dmgame.fragments.ChapterFragment;
import com.hpdxay.hpd3dmgame.fragments.GameFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout mFrameLayout;
    private RadioGroup mRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFrameLayout = (FrameLayout) findViewById(R.id.id_main_container);
        mRadioGroup = (RadioGroup) findViewById(R.id.id_main_group);

        mRadioGroup.setOnCheckedChangeListener(this);
        mRadioGroup.check(R.id.id_main_rb_chapter);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        switch (checkedId) {
            case R.id.id_main_rb_chapter:
                ChapterFragment chapterFragment = new ChapterFragment();
                transaction.replace(R.id.id_main_container, chapterFragment);
                setTitle("文章");
                break;
            case R.id.id_main_rb_luntang:
                setTitle("论坛");
                break;
            case R.id.id_main_rb_game:
                GameFragment gameFragment = new GameFragment();
                transaction.replace(R.id.id_main_container, gameFragment);
                setTitle("游戏");
                break;
        }
        transaction.commit();
    }
}
