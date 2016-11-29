package com.studio321.app.androidstudy.util.permission;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.ActivityCompat;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Permission 획득 Acitvity.
 *
 * Created by wooyoung on 2016. 11. 28..
 */

public class PermissionActivity extends Activity {

    public static final String EXTRA_PERMISSIONS = "permissions";

    public static final int REQUEST_PERMISSION = 2000;

    private String[] mPermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        Intent intent = getIntent();
        mPermissions = intent.getStringArrayExtra(EXTRA_PERMISSIONS);

        ActivityCompat.requestPermissions(
                this,
                mPermissions,
                REQUEST_PERMISSION
        );

//        mPermissions = savedInstanceState.getStringArray(EXTRA_PERMISSIONS);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        ArrayList<String> deniedPermissions = new ArrayList<>();


        for (int i = 0; i < permissions.length; i++) {
            String permission = permissions[i];
            if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                deniedPermissions.add(permission);
            }
        }

        if (deniedPermissions.isEmpty()) {
            // 모든 권한 획득.
            PermissionManager.getInstance().permissionGranted();

        } else {
            // 권한 획득 실패시.
            PermissionManager.getInstance().permissionDenied();
        }

        finish();
    }
}
