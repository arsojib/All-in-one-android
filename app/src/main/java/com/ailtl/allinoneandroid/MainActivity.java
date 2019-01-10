package com.ailtl.allinoneandroid;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RemoteViews;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setupViewPager(ViewPager viewPager) {
        FragmentViewPagerAdapter adapter = new FragmentViewPagerAdapter(getSupportFragmentManager());
        FragmentViewPagerAdapter.mFragmentList.clear();
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(0);
    }

    private void customPopup(TextView textView) {
        PopupMenu popupMenu = new PopupMenu(this, textView);
        popupMenu.inflate(0);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
    }

//    private void customNotification() {
//        Intent intent = new Intent(context, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
//        intent.putExtra("notification_type", Configuration.NEW_MESSAGE_TYPE);
//        intent.putExtra("room_id", roomId);
//        intent.putExtra("room_info", roomInfo);
//
//        int requestCode = new Random().nextInt();
//        int notificationCode = new Random().nextInt();
//        try {
//            String id = String.valueOf(roomId.replace("|", ""));
//            if (id.length() > 9) {
//                notificationCode = Integer.parseInt(id.substring(id.length() - 9 ,id.length()));
//            } else {
//                notificationCode = Integer.parseInt(id);
//            }
//        } catch (NumberFormatException ignored) {
//            Log.d("Notification_id", ignored.getMessage());
//        }
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
//
//        RemoteViews contentViews = new RemoteViews(context.getPackageName(), R.layout.new_message_push);
//        contentViews.setTextViewText(R.id.notification_title, roomInfo.getRoomName());
//        contentViews.setTextViewText(R.id.notification_details, roomInfo.getLastMessage());
//        contentViews.setTextViewText(R.id.notification_time, Util.getDateTimeDayTranslation(Util.getTimeDifference(roomInfo.getLastMessageTime())));
//        if (bitmap != null) {
//            contentViews.setImageViewBitmap(R.id.notification_photo, bitmap);
//        } else {
//            contentViews.setImageViewResource(R.id.notification_photo, R.drawable.profile);
//        }
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
//                .setSmallIcon(R.drawable.notification_message)
//                .setCustomContentView(contentViews)
//                .setAutoCancel(true)
//                .setGroup(KEY_NOTIFICATION_GROUP)
//                .setGroupSummary(true)
//                .setPriority(android.app.Notification.PRIORITY_HIGH)
//                .setVibrate(new long[0])
//                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
//                .setContentIntent(pendingIntent);
//
//        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(notificationCode, notificationBuilder.build());
//    }

}
