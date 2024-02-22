package uqac.dim.mafag;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class NotificationService {

    private static final String CHANNEL_ID = "MafagNotificationChannel";
    private static final int NOTIFICATION_ID = 1;

    public static void showNotification(Context context, String title, String message, Intent intent) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MAFAG_Notification_Channel";
            String description = "MAFAG_Notification_Channel_Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.apple)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true);


        Intent actionIntent = new Intent(context, WebActivity.class);
        actionIntent.putExtra("mafagName", title);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, (int) System.currentTimeMillis(), actionIntent, PendingIntent.FLAG_IMMUTABLE);

        // Add the action to the notification
        builder.addAction(R.drawable.icon, title, pendingIntent);

        // Set the main intent for when the notification is clicked
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_IMMUTABLE
                );
        builder.setContentIntent(resultPendingIntent);

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
