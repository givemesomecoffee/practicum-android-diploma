package ru.practicum.android.diploma.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.practicum.android.diploma.R

fun Context.shareLink(link: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, link)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(
        sendIntent,
        resources.getString(R.string.select_application_to_send)
    )
    startActivity(shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
}

fun Context.openPhoneApp(phoneNumber: String) {
    startActivity(Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    })
}

fun Context.openEmailApp(email: String) {
    startActivity(Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:$email")
    })
}