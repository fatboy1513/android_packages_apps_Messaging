package com.android.messaging.receiver;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;

import com.android.messaging.datamodel.action.MarkAsReadAction;

public class CopyVerifyCodeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String conversationId = intent.getStringExtra("conversation_id");
        String code = intent.getStringExtra("verify_code");

        MarkAsReadAction.markAsRead(conversationId);
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Verification Code", code);
        clipboard.setPrimaryClip(clip);

        NotificationManager nm = 
            (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = conversationId.hashCode();
        nm.cancel(notificationId);
    }
}
