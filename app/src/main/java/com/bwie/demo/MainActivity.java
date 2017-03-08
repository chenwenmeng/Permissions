package com.bwie.demo;

import android.Manifest;


import android.content.Intent;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.util.Log;
import android.view.View;


import android.widget.Toast;


import java.util.List;


import pub.devrel.easypermissions.EasyPermissions;

public
class
MainActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                //获取6.0权限

                String[] perms={
                        Manifest.permission.CAMERA,};
                //判断是否已经拿到权限
                if(EasyPermissions.hasPermissions(MainActivity.this,perms)){

                    Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(it, 0);

                }else {
                    //请求用户权限
                    EasyPermissions.requestPermissions(MainActivity.this,"拍照需要相机权限",1,perms);

                }



            }
        });
    }
    //权限回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 调用onRequestPermissionsResult
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    //处理成功授权（一定要实现一个接口）
    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {


        Intent it=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(it, 0);

    }

    //处理拒绝授权
    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(MainActivity.this,"拒绝授权",Toast.LENGTH_SHORT).show();
    }
}
