package com.hpdxay.hpd3dmgame;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.hpdxay.hpd3dmgame.adapters.CommentAdapter;
import com.hpdxay.hpd3dmgame.entities.ChapterCommentListItem;
import com.hpdxay.hpd3dmgame.entities.ChapterCommentListItemDataItem;
import com.hpdxay.hpd3dmgame.utils.MyLog;
import com.hpdxay.hpd3dmgame.utils.NetworkUtil;
import com.hpdxay.hpd3dmgame.widgets.PullToRefreshRecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class ChapterCommentActivity extends AppCompatActivity implements PullToRefreshBase.OnRefreshListener2<RecyclerView> {

    private ProgressBar mProgressBar;
    private PullToRefreshRecyclerView mPullToRefreshRecyclerView;
    private CommentAdapter adapter;
    private EditText mEditText;
    private String id;
    private int pageno = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_comment);

        setTitle("评论详情");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        mPullToRefreshRecyclerView = (PullToRefreshRecyclerView) findViewById(R.id.id_chapter_comment_recycler);
        mEditText = (EditText) findViewById(R.id.id_chapter_comment_edit);
        mProgressBar = (ProgressBar) findViewById(R.id.id_chapter_comment_progress);


        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        mPullToRefreshRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullToRefreshRecyclerView.setOnRefreshListener(this);
        mPullToRefreshRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        adapter = new CommentAdapter(this);
        mPullToRefreshRecyclerView.setAdapter(adapter);
        volleyGet();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //Volley的get请求
    private void volleyGet() {
        mPullToRefreshRecyclerView.setVisibility(View.INVISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
        String url = " http://www.3dmgame.com/sitemap/api.php?type=1&aid=" + id + "&pageno=" + pageno;
        /**
         * 参数1：请求的方法
         * 参数2：网址路径
         * 参数3：请求成功回调
         * 参数4：请求失败回调
         */
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONObject description = jsonObject.getJSONObject("description");
                    JSONArray data = description.getJSONArray("data");
                    ArrayList<ChapterCommentListItemDataItem> items = new ArrayList<>();
                    for (int i = 0; i < data.length(); i++) {
                        ChapterCommentListItemDataItem item = new ChapterCommentListItemDataItem();
                        JSONObject o1 = data.getJSONObject(i);
                        String username = o1.getString("username");
                        item.setUsername(username);
                        String msg = o1.getString("msg");
                        item.setMsg(msg);
                        items.add(item);
                    }
                    adapter.addAll(items);
                    ChapterCommentActivity.this.pageno++;
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    mProgressBar.setVisibility(View.GONE);
                    mPullToRefreshRecyclerView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ChapterCommentActivity.this, "获取失败", Toast.LENGTH_SHORT).show();
                mProgressBar.setVisibility(View.GONE);
                mPullToRefreshRecyclerView.setVisibility(View.VISIBLE);
            }
        });
        //一定要设置Tag,可以通过tag标签进行寻找
        request.setTag("abcGet");
        //把这个请求添加到这个全局队列中
        MyApplication.getHttpQueues().add(request);

    }


    //Volley的post请求
    private void volleyPost(final String aid, final String content) {
        final String url = "http://www.3dmgame.com/sitemap/api.php?type=2";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("xxxx", "onResponse() returned: " + s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String code = jsonObject.getString("code");
                    if (code.equals("1")) {
                        Toast.makeText(ChapterCommentActivity.this, "评论成功", Toast.LENGTH_SHORT).show();
                        mEditText.setText("");
                        //刷新评论
                        ChapterCommentActivity.this.pageno = 1;
                        adapter.clear();
                        volleyGet();
                    } else {
                        Toast.makeText(ChapterCommentActivity.this, "评论失败，请重新提交", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(ChapterCommentActivity.this, "网络错误，请再次提交", Toast.LENGTH_SHORT).show();

            }
        }) {
            //post的请求参数，在这里设置
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("aid", aid);
                hashMap.put("msg", content);
                return hashMap;
            }
        };
        request.setTag("abcPost");
        MyApplication.getHttpQueues().add(request);

    }

    //点击提交评论
    public void submitComment(View view) {
        String content = mEditText.getText().toString().trim();
        if (!TextUtils.isEmpty(content)) {
            volleyPost(id, content);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getHttpQueues().cancelAll("abcGet");
        MyApplication.getHttpQueues().cancelAll("abcPost");
    }


    @Override
    public void onPullDownToRefresh(final PullToRefreshBase<RecyclerView> refreshView) {
        adapter.clear();
        pageno = 1;
        volleyGet();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshRecyclerView.onRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
        volleyGet();
        refreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullToRefreshRecyclerView.onRefreshComplete();
            }
        }, 2000);
    }
}
