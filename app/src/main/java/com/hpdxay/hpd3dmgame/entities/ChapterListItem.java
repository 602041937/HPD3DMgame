package com.hpdxay.hpd3dmgame.entities;

import java.util.Map;

/**
 * Created by hpd on 2016/1/27.
 */
public class ChapterListItem {
    private Map<Integer, ChapterListItemDataItem> data;
    private PagingEntity paging;

    public Map<Integer, ChapterListItemDataItem> getData() {
        return data;
    }

    public void setData(Map<Integer, ChapterListItemDataItem> data) {
        this.data = data;
    }

    public PagingEntity getPaging() {
        return paging;
    }

    public void setPaging(PagingEntity paging) {
        this.paging = paging;
    }
}
