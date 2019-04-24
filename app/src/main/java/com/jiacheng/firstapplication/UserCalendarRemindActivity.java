package com.jiacheng.firstapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.Vibrator;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiacheng.firstapplication.util.StringUtils;

/**
 * 日程提醒
 */
public class UserCalendarRemindActivity extends Activity {

    PowerManager.WakeLock wakeLock;
    MediaPlayer player;

    RelativeLayout container;
    TextView titleTextView;
    TextView actionTimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_calendar_remind);

        String title = getIntent().getStringExtra("title");
        String actionTime = getIntent().getStringExtra("actionTime");
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(actionTime)) {
            finish();
            return;
        }

        container = (RelativeLayout) findViewById(R.id.container);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        actionTimeTextView = (TextView) findViewById(R.id.actionTimeTextView);
        titleTextView.setText(title);
        actionTimeTextView.setText(actionTime);
        container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null && player.isPlaying()) {
                    player.stop();
                } else {
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.cancel();
                }
            }
        });

        // 锁屏后也会显示，会点亮屏幕并保持屏幕常亮
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String remindType = getIntent().getStringExtra("remindType");
        if (remindType != null) {
            PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
            wakeLock = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP
                    | PowerManager.SCREEN_DIM_WAKE_LOCK, "SimpleTimer");
            wakeLock.acquire();
            if (remindType.equals("alerm")) {
                Uri uri = RingtoneManager.getActualDefaultRingtoneUri(this,
                        RingtoneManager.TYPE_RINGTONE);
                player = MediaPlayer.create(this, uri);
                player.setLooping(true);
                player.start();
            } else if (remindType.equals("vibrator")) {
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(new long[] { 0, 500, 1000, 1500, 2000, 2500 },
                        -1);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (wakeLock != null)
            wakeLock.release();
        wakeLock = null;
        if (player != null)
            player.release();
        player = null;
    }

    public static class RemindBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean remind = true; // TODO 后续改为从已存储文件中取值
            if (!remind)
                return;

            String title = intent.getStringExtra("title");
            String actionTime = intent.getStringExtra("actionTime");

            Intent itt = new Intent(context, UserCalendarRemindActivity.class);
            itt.putExtra("title", title);
            itt.putExtra("actionTime", actionTime);
            itt.putExtra("remindType", "alerm"); // TODO 后续改为从已存储文件中取值
            itt.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(itt);
        }
    }
}
