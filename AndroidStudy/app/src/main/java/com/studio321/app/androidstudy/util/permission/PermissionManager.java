package com.studio321.app.androidstudy.util.permission;

import static com.studio321.app.androidstudy.util.permission.PermissionUtil.REQUEST_PERMISSION_RESULT_FAIL;
import static com.studio321.app.androidstudy.util.permission.PermissionUtil.REQUEST_PERMISSION_RESULT_SUCCESS;

/**
 * Created by wooyoung on 2016. 11. 29..
 */

public class PermissionManager {

    private static PermissionManager instance = null;

    private PermissionListener mListener;

    public static PermissionManager getInstance() {
        if(instance==null) {
            instance = new PermissionManager();
        }
        return instance;
    }

    public void setListener(PermissionListener listener) {
        mListener = listener;
    }

    public void permissionGranted() {
        mListener.onPermissionResult(REQUEST_PERMISSION_RESULT_SUCCESS);
    }

    // DENIED
    public void permissionDenied() {
        mListener.onPermissionResult(REQUEST_PERMISSION_RESULT_FAIL);
    }
}
