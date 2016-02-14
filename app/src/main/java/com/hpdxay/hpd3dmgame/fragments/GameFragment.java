package com.hpdxay.hpd3dmgame.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hpdxay.hpd3dmgame.MyApplication;
import com.hpdxay.hpd3dmgame.R;
import com.hpdxay.hpd3dmgame.adapters.GameItemAdapter;
import com.hpdxay.hpd3dmgame.entities.ChapterListItemDataItem;
import com.hpdxay.hpd3dmgame.entities.GameItemEntity;
import com.hpdxay.hpd3dmgame.widgets.PullToRefreshRecyclerViewGrid;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private PullToRefreshRecyclerViewGrid recycler;
    private Spinner spinner;
    private GameItemAdapter adapter;
    private List<GameItemEntity> list = new ArrayList<>();

    public GameFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_game, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recycler = (PullToRefreshRecyclerViewGrid) view.findViewById(R.id.id_game_recycler);
        spinner = (Spinner) view.findViewById(R.id.id_game_spinner);

//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);
//        recycler.setLayoutManager(gridLayoutManager);

        ArrayList<String> list = new ArrayList<>();
        list.add("游戏总分类");
        list.add("动作(ACT)");
        list.add("射击(FPS)");
        list.add("角色扮演(RPG)");
        list.add("养成(GAL)");
        list.add("益智(PUZ");
        list.add("即时战略(RTS)");
        list.add("策略(SLG)");
        list.add("体育(SPG)");
        list.add("模拟经营(SIM)");
        list.add("赛车(RAC)");
        list.add("冒险(AVG)");
        list.add("动作角色(ARPG)");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, list);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        adapter = new GameItemAdapter(getContext());
        recycler.setAdapter(adapter);

        // volleyGet("179");
        spinner.setOnItemSelectedListener(this);
    }

    //Volley的get请求
    private void volleyGet(String typeid) {
        String url = "http://www.3dmgame.com/sitemap/api.php?row=12&typeid=" + typeid + "&paging=1&page=1";
        /**
         * 参数1：请求的方法
         * 参数2：网址路径
         * 参数3：请求成功回调
         * 参数4：请求失败回调
         */
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.d("hpddd", "onResponse() returned: " + s);
                int index = s.indexOf("data");
                String substring = s.substring(index - 2);
                Log.d("hpddd", "onResponse() 2: " + substring);
                getData(substring);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("hpddd", "onResponse() returned: 失败了");
            }
        });
        //一定要设置Tag,可以通过tag标签进行寻找
        request.setTag("gameGet");
        //把这个请求添加到这个全局队列中
        MyApplication.getHttpQueues().add(request);

    }

    public void getData(String json) {

        try {
            JSONObject object = new JSONObject(json);
            JSONObject data = object.getJSONObject("data");
            int i = 0;
            m:
            while (true) {
                GameItemEntity item = new GameItemEntity();
                String s = data.optString(String.valueOf(i));
                Log.i("hpddd", "hopd:" + s);
                if (TextUtils.isEmpty(s)) {
                    break m;
                }
                i++;
                Log.i("hpddd", String.valueOf(i));
                JSONObject object1 = new JSONObject(s);
                item.setShorttitle(object1.getString("shorttitle"));
                item.setLitpic(object1.getString("litpic"));
                item.setShorttitle(object1.getString("shorttitle"));
                item.setTid(object1.getString("tid"));
                item.setMade_company(object1.getString("made_company"));
                item.setRelease_date(object1.getString("release_date"));
                item.setRelease_company(object1.getString("release_company"));
                item.setWebsit(object1.getString("websit"));
                item.setTerrace(object1.getString("terrace"));
                list.add(item);
            }
            adapter.addAll(list);
        } catch (JSONException e) {
            Log.i("hpddd", "异常了");
            e.printStackTrace();
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String typeid = null;
        switch (position) {
            case 0:
                typeid = "179";
                break;
            case 1:
                typeid = "181";
                break;
            case 2:
                typeid = "182";
                break;
            case 3:
                typeid = "183";
                break;
            case 4:
                typeid = "184";
                break;
            case 5:
                typeid = "185";
                break;
            case 6:
                typeid = "186";
                break;
            case 7:
                typeid = "187";
                break;
            case 8:
                typeid = "188";
                break;
            case 9:
                typeid = "189";
                break;
            case 10:
                typeid = "190";
                break;
            case 11:
                typeid = "191";
                break;
            case 12:
                typeid = "192";
                break;
        }
        adapter.clear();
        list.clear();
        volleyGet(typeid);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
