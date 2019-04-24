package com.jiacheng.firstapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    //标题
    private TextView titleTextView;
    //ViewPager
    private ViewPager viewPager;
    //右下角扇形菜单
    private RelativeLayout cornerMenuOpenRelativeLayout;
    private ImageView settingImageView;                 //设置
    private ImageView calendarImageView;                //日历
    private ImageView personalImageView;                //个人中心
    private ImageView shoppingBagImageView;             //购物袋
    private ImageView showCornerMenuImageView;          //展开/收缩

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        titleTextView = findViewById(R.id.titleTextView);
        viewPager = findViewById(R.id.viewPager);
        cornerMenuOpenRelativeLayout = findViewById(R.id.cornerMenuOpenRelativeLayout);
        settingImageView = findViewById(R.id.settingImageView);
        calendarImageView = findViewById(R.id.calendarImageView);
        personalImageView = findViewById(R.id.personalImageView);
        shoppingBagImageView = findViewById(R.id.shoppingBagImageView);
        showCornerMenuImageView = findViewById(R.id.showCornerMenuImageView);

        titleTextView.setText("礼物推荐");
        init();
    }

    boolean checkLogin() {
        App app = (App) getApplication();
        if (app.user == null || app.user.mobileToken == null) {
            Dialogs.showSimpleDialog(this, "登录后才能继续操作，您现在登录吗？", true,
                    new Dialogs.OnOkListener() {
                        @Override
                        public void onOk() {
                            Intent intent = new Intent(getApplicationContext(),
                                    LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            return false;
        }
        return true;
    }

    private void init() {
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        titleTextView.setText("礼物推荐");
                        break;
                    case 1:
                        titleTextView.setText("礼品中心");
                        break;
                    case 2:
                        titleTextView.setText("礼品推荐");
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return o == view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//                super.destroyItem(container, position, object);
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
//                return super.instantiateItem(container, position);
                final Context context = getApplicationContext();
                View v = null;

                if (position == 0) {
                    v = getLayoutInflater().inflate(R.layout.index_recommend, viewPager, false);
                    ViewFlipper vf = v.findViewById(R.id.topViewFlipper);
                    vf.setInAnimation(context, R.anim.in_from_right);
                    vf.setOutAnimation(context, R.anim.out_to_left);
                    ViewFlipper.LayoutParams params = new ViewFlipper.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);

                    ImageView iv1 = new ImageView(context);
                    iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv1.setImageResource(R.drawable.index_recommend_top1);
                    vf.addView(iv1, params);

                    ImageView iv2 = new ImageView(context);
                    iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv2.setImageResource(R.drawable.index_recommend_top2);
                    vf.addView(iv2, params);

                    ImageView iv3 = new ImageView(context);
                    iv3.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv3.setImageResource(R.drawable.index_recommend_top3);
                    vf.addView(iv3, params);

                    ImageView bottomLeft = v.findViewById(R.id.bottomLeftImageView);
                    bottomLeft.setImageResource(R.drawable.index_recommend_leftbottom);
                    ImageView bottomRight = v.findViewById(R.id.bottomRightImageView);
                    bottomRight.setImageResource(R.drawable.index_recommend_rightbottom);
                } else if (position == 1) {
                    v = new ImageView(context);
                    ImageView iv = (ImageView) v;
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv.setImageResource(R.drawable.index_giftcenter);
                } else if (position == 2) {
                    v = new ImageView(context);
                    ImageView iv = (ImageView) v;
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    iv.setImageResource(R.drawable.index_strategy);
                }
                final ViewGroup.LayoutParams p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.addView(v, p);
                return v;
            }
        });

        settingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });
        calendarImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin())
                    return;
                Intent intent = new Intent(getApplicationContext(),
                        UserCalendarActivity.class);
                startActivity(intent);
            }
        });

        shoppingBagImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        personalImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkLogin()) {
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), PersonalActivity.class);
                startActivity(intent);
            }
        });

        cornerMenuOpenRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerMenuOpenRelativeLayout.setVisibility(View.GONE);
                showCornerMenuImageView.setVisibility(View.VISIBLE);
            }
        });

        showCornerMenuImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cornerMenuOpenRelativeLayout.setVisibility(View.VISIBLE);
                showCornerMenuImageView.setVisibility(View.GONE);
            }
        });
    }
}
