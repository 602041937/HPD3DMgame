package com.hpdxay.hpd3dmgame.entities;

/**
 * Created by hpd on 2016/1/27.
 */
public class PagingEntity {

    private String totalrow;
    private String totalpage;
    private int page;

    public String getTotalrow() {
        return totalrow;
    }

    public void setTotalrow(String totalrow) {
        this.totalrow = totalrow;
    }

    public String getTotalpage() {
        return totalpage;
    }

    public void setTotalpage(String totalpage) {
        this.totalpage = totalpage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
