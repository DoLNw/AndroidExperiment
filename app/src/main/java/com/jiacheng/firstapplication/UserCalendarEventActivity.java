package com.jiacheng.firstapplication;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.jiacheng.firstapplication.model.UserCalendar;
import com.ta.utdid2.android.utils.StringUtils;

/**
 * 日历事件
 */
public class UserCalendarEventActivity extends BaseActivity {

    UserCalendar userCalendar = new UserCalendar();

    EditText titleEditText;
    Button actionTimeButton;
    LinearLayout remind1, remind2, remind3, remind4, remind5;
    Spinner remindTime1Spinner, remindTime2Spinner, remindTime3Spinner,
            remindTime4Spinner, remindTime5Spinner;
    ImageView deleteRemind1ImageView, deleteRemind2ImageView,
            deleteRemind3ImageView, deleteRemind4ImageView,
            deleteRemind5ImageView;
    Button addRemindButton;
    Button saveButton;

    public UserCalendarEventActivity() {
        super(R.layout.activity_user_calendar_event, "用户日程");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userCalendar.id = getIntent().getIntExtra("userCalendarId", 0);
        userCalendar.actionTime = getIntent().getStringExtra("actionTime");
        if (userCalendar.id == 0) {
            titleTextView.setText("添加日程");
        } else {
            userCalendar.title = getIntent().getStringExtra("title");
            userCalendar.remindTime1 = getIntent()
                    .getIntExtra("remindTime1", 0);
            userCalendar.remindTime2 = getIntent()
                    .getIntExtra("remindTime2", 0);
            userCalendar.remindTime3 = getIntent()
                    .getIntExtra("remindTime3", 0);
            userCalendar.remindTime4 = getIntent()
                    .getIntExtra("remindTime4", 0);
            userCalendar.remindTime5 = getIntent()
                    .getIntExtra("remindTime5", 0);
        }

        titleEditText = (EditText) findViewById(R.id.titleEditText);
        actionTimeButton = (Button) findViewById(R.id.actionTimeButton);
        remind1 = (LinearLayout) findViewById(R.id.remind1);
        remind2 = (LinearLayout) findViewById(R.id.remind2);
        remind3 = (LinearLayout) findViewById(R.id.remind3);
        remind4 = (LinearLayout) findViewById(R.id.remind4);
        remind5 = (LinearLayout) findViewById(R.id.remind5);
        remindTime1Spinner = (Spinner) findViewById(R.id.remindTime1Spinner);
        remindTime2Spinner = (Spinner) findViewById(R.id.remindTime2Spinner);
        remindTime3Spinner = (Spinner) findViewById(R.id.remindTime3Spinner);
        remindTime4Spinner = (Spinner) findViewById(R.id.remindTime4Spinner);
        remindTime5Spinner = (Spinner) findViewById(R.id.remindTime5Spinner);
        deleteRemind1ImageView = (ImageView) findViewById(R.id.deleteRemind1ImageView);
        deleteRemind2ImageView = (ImageView) findViewById(R.id.deleteRemind2ImageView);
        deleteRemind3ImageView = (ImageView) findViewById(R.id.deleteRemind3ImageView);
        deleteRemind4ImageView = (ImageView) findViewById(R.id.deleteRemind4ImageView);
        deleteRemind5ImageView = (ImageView) findViewById(R.id.deleteRemind5ImageView);
        addRemindButton = (Button) findViewById(R.id.addRemindButton);
        saveButton = (Button) findViewById(R.id.saveButton);

        ArrayAdapter<RemindTime> aa = new ArrayAdapter<RemindTime>(
                getApplicationContext(), R.layout.spinner_item, REMIND_TIMES);
        remindTime1Spinner.setAdapter(aa);
        remindTime2Spinner.setAdapter(aa);
        remindTime3Spinner.setAdapter(aa);
        remindTime4Spinner.setAdapter(aa);
        remindTime5Spinner.setAdapter(aa);

        actionTimeButton.setText(userCalendar.actionTime);
        titleEditText.setText(userCalendar.title);
        showReminds();

        final OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String date = actionTimeButton.getText().toString()
                        .substring(0, 10);
                actionTimeButton.setText(date + " "
                        + (hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay)
                        + ":" + (minute < 10 ? "0" + minute : "" + minute));
            }
        };
        actionTimeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String time = actionTimeButton.getText().toString();
                int hour = 12;
                int minute = 0;
                try {
                    hour = Integer.parseInt(time.substring(11, 13));
                    minute = Integer.parseInt(time.substring(14, 16));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                final TimePickerDialog dialog = new TimePickerDialog(
                        UserCalendarEventActivity.this, onTimeSetListener,
                        hour, minute, true);
                dialog.show();
            }
        });

        remindTime1Spinner
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        userCalendar.remindTime1 = ((RemindTime) remindTime1Spinner
                                .getSelectedItem()).minutes;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        remindTime2Spinner
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        userCalendar.remindTime2 = ((RemindTime) remindTime2Spinner
                                .getSelectedItem()).minutes;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        remindTime3Spinner
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        userCalendar.remindTime3 = ((RemindTime) remindTime3Spinner
                                .getSelectedItem()).minutes;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        remindTime4Spinner
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        userCalendar.remindTime4 = ((RemindTime) remindTime4Spinner
                                .getSelectedItem()).minutes;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
        remindTime5Spinner
                .setOnItemSelectedListener(new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent,
                                               View view, int position, long id) {
                        userCalendar.remindTime5 = ((RemindTime) remindTime5Spinner
                                .getSelectedItem()).minutes;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        deleteRemind1ImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userCalendar.remindTime1 = userCalendar.remindTime2;
                userCalendar.remindTime2 = userCalendar.remindTime3;
                userCalendar.remindTime3 = userCalendar.remindTime4;
                userCalendar.remindTime4 = userCalendar.remindTime5;
                userCalendar.remindTime5 = 0;
                showReminds();
            }
        });
        deleteRemind2ImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userCalendar.remindTime2 = userCalendar.remindTime3;
                userCalendar.remindTime3 = userCalendar.remindTime4;
                userCalendar.remindTime4 = userCalendar.remindTime5;
                userCalendar.remindTime5 = 0;
                showReminds();
            }
        });
        deleteRemind3ImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userCalendar.remindTime3 = userCalendar.remindTime4;
                userCalendar.remindTime4 = userCalendar.remindTime5;
                userCalendar.remindTime5 = 0;
                showReminds();
            }
        });
        deleteRemind4ImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userCalendar.remindTime4 = userCalendar.remindTime5;
                userCalendar.remindTime5 = 0;
                showReminds();
            }
        });
        deleteRemind5ImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userCalendar.remindTime5 = 0;
                showReminds();
            }
        });

        addRemindButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userCalendar.remindTime5 != 0)
                    return;
                addRemindButton.setText("添加提醒");
                if (userCalendar.remindTime4 != 0) {
                    remind5.setVisibility(View.VISIBLE);
                    userCalendar.remindTime5 = ((RemindTime) remindTime5Spinner
                            .getSelectedItem()).minutes;
                    addRemindButton.setVisibility(View.GONE);
                    return;
                }
                if (userCalendar.remindTime3 != 0) {
                    remind4.setVisibility(View.VISIBLE);
                    userCalendar.remindTime4 = ((RemindTime) remindTime4Spinner
                            .getSelectedItem()).minutes;
                    return;
                }
                if (userCalendar.remindTime2 != 0) {
                    remind3.setVisibility(View.VISIBLE);
                    userCalendar.remindTime3 = ((RemindTime) remindTime3Spinner
                            .getSelectedItem()).minutes;
                    return;
                }
                if (userCalendar.remindTime1 != 0) {
                    remind2.setVisibility(View.VISIBLE);
                    userCalendar.remindTime2 = ((RemindTime) remindTime2Spinner
                            .getSelectedItem()).minutes;
                    return;
                }
                remind1.setVisibility(View.VISIBLE);
                userCalendar.remindTime1 = ((RemindTime) remindTime1Spinner
                        .getSelectedItem()).minutes;
            }
        });

        saveButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                userCalendar.title = titleEditText.getText().toString().trim();
                userCalendar.actionTime = actionTimeButton.getText().toString();
                if (StringUtils.isEmpty(userCalendar.title)) {
                    showMessage("请输入日程内容");
                    return;
                }
                // TODO 后续加上保存到服务器功能
                setUserCalendarRemind();
            }
        });
    }

    void showReminds() {
        remind1.setVisibility(View.GONE);
        remind2.setVisibility(View.GONE);
        remind3.setVisibility(View.GONE);
        remind4.setVisibility(View.GONE);
        remind5.setVisibility(View.GONE);

        remindTime1Spinner.setSelection(0);
        remindTime2Spinner.setSelection(0);
        remindTime3Spinner.setSelection(0);
        remindTime4Spinner.setSelection(0);
        remindTime5Spinner.setSelection(0);

        int remindCount = 0;
        if (userCalendar.remindTime1 != 0)
            remindCount++;
        if (userCalendar.remindTime2 != 0)
            remindCount++;
        if (userCalendar.remindTime3 != 0)
            remindCount++;
        if (userCalendar.remindTime4 != 0)
            remindCount++;
        if (userCalendar.remindTime5 != 0)
            remindCount++;

        if (remindCount == 0) {
            addRemindButton.setText("设置提醒");
            addRemindButton.setVisibility(View.VISIBLE);
        } else if (remindCount > 0 && remindCount < 5) {
            addRemindButton.setText("添加提醒");
            addRemindButton.setVisibility(View.VISIBLE);
        } else if (remindCount == 5) {
            addRemindButton.setVisibility(View.GONE);
        }

        if (userCalendar.remindTime1 != 0) {
            remind1.setVisibility(View.VISIBLE);
            RemindTime.select(remindTime1Spinner, userCalendar.remindTime1);
            if (userCalendar.remindTime2 != 0) {
                remind2.setVisibility(View.VISIBLE);
                RemindTime.select(remindTime2Spinner, userCalendar.remindTime2);
                if (userCalendar.remindTime3 != 0) {
                    remind3.setVisibility(View.VISIBLE);
                    RemindTime.select(remindTime3Spinner,
                            userCalendar.remindTime3);
                    if (userCalendar.remindTime4 != 0) {
                        remind4.setVisibility(View.VISIBLE);
                        RemindTime.select(remindTime4Spinner,
                                userCalendar.remindTime4);
                        if (userCalendar.remindTime5 != 0) {
                            remind5.setVisibility(View.VISIBLE);
                            RemindTime.select(remindTime5Spinner,
                                    userCalendar.remindTime5);
                            addRemindButton.setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
    }

    public void setUserCalendarRemind() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                Locale.US);
        Date now = new Date();
        try {
            Date actionTime = sdf.parse(userCalendar.actionTime);
            if (!actionTime.after(now))
                return;
            Calendar calendar = Calendar.getInstance();
            ArrayList<Long> remindTimes = new ArrayList<Long>();

            calendar.setTime(actionTime);
            remindTimes.add(calendar.getTimeInMillis());
            if (userCalendar.remindTime1 > 0) {
                calendar.setTime(actionTime);
                calendar.add(Calendar.MINUTE, -userCalendar.remindTime1);
                if (calendar.getTime().after(now))
                    remindTimes.add(calendar.getTimeInMillis());
            }
            if (userCalendar.remindTime2 > 0) {
                calendar.setTime(actionTime);
                calendar.add(Calendar.MINUTE, -userCalendar.remindTime2);
                if (calendar.getTime().after(now))
                    remindTimes.add(calendar.getTimeInMillis());
            }
            if (userCalendar.remindTime3 > 0) {
                calendar.setTime(actionTime);
                calendar.add(Calendar.MINUTE, -userCalendar.remindTime3);
                if (calendar.getTime().after(now))
                    remindTimes.add(calendar.getTimeInMillis());
            }
            if (userCalendar.remindTime4 > 0) {
                calendar.setTime(actionTime);
                calendar.add(Calendar.MINUTE, -userCalendar.remindTime4);
                if (calendar.getTime().after(now))
                    remindTimes.add(calendar.getTimeInMillis());
            }
            if (userCalendar.remindTime5 > 0) {
                calendar.setTime(actionTime);
                calendar.add(Calendar.MINUTE, -userCalendar.remindTime5);
                if (calendar.getTime().after(now))
                    remindTimes.add(calendar.getTimeInMillis());
            }

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            for (Long time : remindTimes) {
                Intent intent = new Intent(
                        this,
                        UserCalendarRemindActivity.RemindBroadcastReceiver.class);
                intent.putExtra("title", userCalendar.title);
                intent.putExtra("actionTime", userCalendar.actionTime);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this,
                        userCalendar.id, intent, Intent.FLAG_ACTIVITY_NEW_TASK);
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class RemindTime {
        public int minutes;
        public String name;

        public RemindTime(int minutes, String name) {
            super();
            this.minutes = minutes;
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        static void select(Spinner spinner, int minutes) {
            for (int i = 0; i < spinner.getCount(); i++) {
                RemindTime rt = (RemindTime) spinner.getItemAtPosition(i);
                if (rt.minutes == minutes) {
                    spinner.setSelection(i);
                    break;
                }
            }
        }
    }

    static final RemindTime[] REMIND_TIMES = {
            new RemindTime(10, "提前 10 分钟提醒"), new RemindTime(60, "提前 1 小时提醒"),
            new RemindTime(360, "提前 6 小时提醒"),
            new RemindTime(720, "提前 12 小时提醒"),
            new RemindTime(1440, "提前 1 天提醒"), new RemindTime(4320, "提前 3 天提醒"),
            new RemindTime(10080, "提前 1 星期提醒") };

}