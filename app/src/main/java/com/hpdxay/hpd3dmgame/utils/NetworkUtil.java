package com.hpdxay.hpd3dmgame.utils;

import com.hpdxay.hpd3dmgame.entities.ChapterCommentListItem;
import com.hpdxay.hpd3dmgame.entities.ChapterListItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hpd on 2016/1/27.
 */
public class NetworkUtil {

    public interface Service {
        @GET("/sitemap/api.php?&paging=1")
        Call<ChapterListItem> getChapterListItem(@Query("row") int row, @Query("typeid") String typeid, @Query("page") int page);

        @GET("/sitemap/api.php?&type=1")
        Call<ChapterCommentListItem> getChapterCommentListItem(@Query("aid") String aid, @Query("pageno") int pageno);

    }
}
