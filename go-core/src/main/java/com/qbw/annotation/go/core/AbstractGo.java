package com.qbw.annotation.go.core;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.qbw.log.XLog;

/**
 * @author qbw
 * @createtime 2016/08/29 17:43
 */


public abstract class AbstractGo implements IGo {

    protected Context mFromAct;
    protected Class mGoToCls;

    protected Bundle mBundle;

    @Override
    public void go() {
        if (null == mFromAct) {
            XLog.e("method 'from' is not be called!");
            return;
        }
        if (null == mGoToCls) {
            XLog.e("method 'to' is not be called");
            return;
        }
        Intent intent = new Intent(mFromAct, mGoToCls);
        if (!(mFromAct instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (null != mBundle) {
            intent.putExtras(mBundle);
        }
        mFromAct.startActivity(intent);
    }

    @Override
    public void go(int requestCode) {
        go(requestCode, null);
    }


    @Override
    public void go(Bundle bundle) {
        if (null == mFromAct) {
            XLog.e("method 'from' is not be called!");
            return;
        }
        if (null == mGoToCls) {
            XLog.e("method 'to' is not be called");
            return;
        }
        if (!(mFromAct instanceof Activity)) {
            XLog.e("goForResult, from must be a activity!");
            return;
        }
        Intent intent = new Intent(mFromAct, mGoToCls);
        if (null != mBundle) {
            intent.putExtras(mBundle);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mFromAct.startActivity(intent, bundle);
        } else {
            XLog.e("go(Bundle bundle) need sdk >= 16");
        }
    }

    @Override
    public void go(int requestCode, Bundle bundle) {
        if (null == mFromAct) {
            XLog.e("method 'from' is not be called!");
            return;
        }
        if (null == mGoToCls) {
            XLog.e("method 'to' is not be called");
            return;
        }
        if (!(mFromAct instanceof Activity)) {
            XLog.e("goForResult, from must be a activity!");
            return;
        }
        Intent intent = new Intent(mFromAct, mGoToCls);
        if (null != mBundle) {
            intent.putExtras(mBundle);
        }
        if (bundle == null) {
            ((Activity) mFromAct).startActivityForResult(intent, requestCode);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                ((Activity) mFromAct).startActivityForResult(intent, requestCode, bundle);
            } else {
                XLog.e("public void go(int requestCode, Bundle bundle) {\n need sdk >= 16");
            }
        }
    }

    public Bundle extract() {
        return mBundle;
    }
}
