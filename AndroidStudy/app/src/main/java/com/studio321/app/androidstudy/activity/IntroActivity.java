package com.studio321.app.androidstudy.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.studio321.app.androidstudy.MainActivity;
import com.studio321.app.androidstudy.R;
import com.studio321.app.androidstudy.util.permission.PermissionListener;
import com.studio321.app.androidstudy.util.permission.PermissionUtil;

/**
 * Intro Activity
 *
 * Created by wooyoung on 2016. 11. 28..
 */
public class IntroActivity extends Activity {

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        mContext = this;

        // TODO. Intro Animation 구현.
        // TODO. Intro Animation 리소스 구하기.


        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionResult(int result) {
                // Permission 결과 리턴.

                if (result == PermissionUtil.REQUEST_PERMISSION_RESULT_SUCCESS) {
                    // 퍼미션 검사 확인.
                    startMainActivity();
                } else {
                    // 팝업 생성.
                    // TODO. 커스텀 팝업 구현.
                }
            }
        };

        // TODO. Permission Check 로직 구현.
        new PermissionUtil(mContext, permissionListener).requestPermission(Manifest.permission.SEND_SMS);
    }

    /**
     * 메인 Activity로 이동.
     */
    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
