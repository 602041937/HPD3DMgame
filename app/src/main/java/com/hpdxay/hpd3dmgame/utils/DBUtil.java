package com.hpdxay.hpd3dmgame.utils;

import android.content.Context;

import com.hpdxay.hpd3dmgame.dao.DaoMaster;
import com.hpdxay.hpd3dmgame.dao.DaoSession;

/**
 * Created by hpd on 2016/1/29.
 */
public class DBUtil {

    private static DaoSession session;

    public static void initialize(Context context) {

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "hpd3dmgame.db", null);
        DaoMaster master = new DaoMaster(helper.getWritableDatabase());
        session = master.newSession();
    }

    public static DaoSession getSession() {
        return session;
    }
}
