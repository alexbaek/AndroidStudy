package com.studio321.app.androidstudy.util.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;

/**
 * Permission Check Util
 *
 * Created by wooyoung on 2016. 11. 28..
 */

public class PermissionUtil {

    private Context mContext;
    private PermissionManager mPermssionManager;

    // Permissions
    public static final int REQUEST_PERMISSION_RESULT_SUCCESS   = 1000;
    public static final int REQUEST_PERMISSION_RESULT_FAIL      = 1001;

    public PermissionUtil(Context context, PermissionListener listener) {
        mContext = context;
        mPermssionManager = PermissionManager.getInstance();
        mPermssionManager.setListener(listener);
    }

    /**
     * Permission Check
     * @param permission
     */
    public void requestPermission(String permission) {

        // M버전 이하 일때에 대한 처리.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            mPermssionManager.permissionGranted();
            return;
        }

        int checkPermission = ActivityCompat.checkSelfPermission(mContext, permission);

        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            String[] requestList = new String[1];
            requestList[0] = permission;

            // Permission Activity로 전달.
            Intent intent = new Intent(mContext, PermissionActivity.class);
            intent.putExtra(PermissionActivity.EXTRA_PERMISSIONS, requestList);
            mContext.startActivity(intent);

//            ActivityCompat.requestPermissions(
//                    (Activity) mContext,
//                    requestList,
//                    REQUEST_PERMISSION_RESULT
//            );
        } else {
            // 권한 체크가 되어 있음.
            mPermssionManager.permissionGranted();
        }
    }

    public void requestPermissions(ArrayList<String> permissions) {

    }


}
