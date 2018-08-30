package shuanglong.android.m.permission;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import shuanglong.android.m.permission.annotation.PermissionDenied;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionUtils.requestPermission();

    }

    /**
     * 这个算是一次请求，方法名必须不一样
     * */
    @PermissionDenied(requestCode = 100, permissions = {Manifest.permission.CAMERA})
    public void PermissionDenied(List<String> permissionList, List<Integer> requestResult) {

    }


}
