package com.android.messaging.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.messaging.datamodel.action.MarkAsReadAction;

public class MarkAsReadReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String conversationId = intent.getStringExtra("conversation_id");
        Log.e("cjw", "cjw MarkAsReadReceiver.onReceive " + conversationId);
        // 直接调用短信应用的内部方法标记为已读
        MarkAsReadAction.markAsRead(conversationId);
        // 取消通知
        NotificationManager nm = 
            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = conversationId.hashCode();
        nm.cancel(notificationId);
    }
}
