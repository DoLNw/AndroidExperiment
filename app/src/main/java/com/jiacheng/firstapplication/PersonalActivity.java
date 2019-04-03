package com.jiacheng.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ta.utdid2.android.utils.StringUtils;

/**
 * 个人中心
 */
public class PersonalActivity extends BaseActivity {

    Button myInfoButton, //
            changePasswordButton, //
            userAddressButton,//
            orderButton, //
            pointButton, //
            scanButton, //
            favorityButton;//

    public PersonalActivity() {
        super(R.layout.activity_personal, "个人中心");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myInfoButton = findViewById(R.id.myInfoButton);
        changePasswordButton = findViewById(R.id.changePasswordButton);
        userAddressButton = findViewById(R.id.userAddressButton);
        orderButton = findViewById(R.id.orderButton);
        pointButton = findViewById(R.id.pointButton);
        favorityButton = findViewById(R.id.favorityButton);
        scanButton = findViewById(R.id.scanButton);

        App app = (App) getApplication();
        if (app.user == null || StringUtils.isEmpty(app.user.mobileToken))
            quitView.setVisibility(View.GONE);
        else
            quitView.setVisibility(View.VISIBLE);

        myInfoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        MyActivity.class);
                startActivity(intent);
            }
        });
        changePasswordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        userAddressButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        UserAddressListActivity.class);
                startActivity(intent);
            }
        });
        orderButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
            }
        });
        pointButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
            }
        });
        favorityButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        FavoriteActivity.class);
                startActivity(intent);
            }
        });
        scanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                // TODO 后续章节实现此功能
            }
        });
    }

}
