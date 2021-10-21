package com.example.nxappc.domain.misc

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.core.net.toUri
import coil.annotation.ExperimentalCoilApi
import com.example.nxappc.MainActivity
import com.example.nxappc.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@ExperimentalCoilApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
fun CreateNotificationBuilder(context: Context, channelId: String, title: String, content: String): NotificationCompat.Builder {
    val deepLinkIntent = Intent(
        Intent.ACTION_VIEW,
        "myapp://${Routes.PaymentFinished}".toUri(),
        context,
        MainActivity::class.java
    )

    val deepLinkPendingIntent: PendingIntent? = TaskStackBuilder.create(context).run {
        addNextIntentWithParentStack(deepLinkIntent)
        getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    return NotificationCompat.Builder(context, channelId)
        .setSmallIcon(R.drawable.shop_logo)
        .setContentTitle(title)
        .setContentText(content)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(deepLinkPendingIntent)
        .setAutoCancel(true)
}