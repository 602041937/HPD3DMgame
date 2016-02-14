package com.hpdxay.hpd3dmgame;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

public class GameContentActivity extends AppCompatActivity {

    private SimpleDraweeView image;
    private TextView tvShortTitle;
    private TextView tvTid;
    private TextView tvMadeCompany;
    private TextView tvReleaseDate;
    private TextView tvReleaseCompany;
    private TextView tvWebsit;
    private TextView tvTerrace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_content);

        //设置返回键
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setTitle("游戏详情");

        image = (SimpleDraweeView) findViewById(R.id.id_game__content_icon);
        tvShortTitle = (TextView) findViewById(R.id.id_game__content_title);
        tvTid = (TextView) findViewById(R.id.id_game__content_tid);
        tvMadeCompany = (TextView) findViewById(R.id.id_game__content_made_company);
        tvReleaseDate = (TextView) findViewById(R.id.id_game__content_release_date);
        tvReleaseCompany = (TextView) findViewById(R.id.id_game__content_release_company);
        tvWebsit = (TextView) findViewById(R.id.id_game__content_websit);
        tvTerrace = (TextView) findViewById(R.id.id_game__content_terrace);

        final Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String pic = bundle.getString("pic");
        String shorttitle = bundle.getString("shorttitle");
        String tid = bundle.getString("tid");
        String made_company = bundle.getString("made_company");
        String release_date = bundle.getString("release_date");
        String release_company = bundle.getString("release_company");
        final String websit = bundle.getString("websit");
        String terrace = bundle.getString("terrace");

        //设置uri
        Uri uri = Uri.parse(pic);
        //根据uri创建请求图片的请求体
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        //根据请求体来获取控制器
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(image.getController())
                .build();
        image.setController(controller);

        tvShortTitle.setText("游戏名称： " + shorttitle);
        tvTid.setText("游戏类型： " + tid);
        tvMadeCompany.setText("开发厂商： " + made_company);
        tvReleaseDate.setText("发售日期： " + release_date);
        tvReleaseCompany.setText("发售厂商： " + release_company);
        tvWebsit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GameContentActivity.this, WebsitActivity.class);
                intent1.putExtra("path", websit);
                startActivity(intent1);
            }
        });
        tvTerrace.setText("游戏平台： " + terrace);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
