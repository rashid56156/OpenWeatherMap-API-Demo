package com.ow.forecast.utilities

import android.app.Activity
import android.view.Gravity
import androidx.core.content.ContextCompat
import com.tapadoo.alerter.Alerter
import com.ow.forecast.R
import com.ow.forecast.WeatherApplication

object AlertView {
    fun showErrorMsg(activity: Activity, messageText: CharSequence) {
        Alerter.create(activity).setText(messageText).setContentGravity(Gravity.START).setIcon(ContextCompat.getDrawable(WeatherApplication.getInstance(), R.drawable.ic_error_alert)!!).setBackgroundColorRes(R.color.red).show()
    }

    fun showWarningMsg(activity: Activity, messageText: CharSequence) {
        Alerter.create(activity).setText(messageText).setContentGravity(Gravity.START).setIcon(ContextCompat.getDrawable(WeatherApplication.getInstance(), R.drawable.ic_warning_alert)!!).setBackgroundColorRes(R.color.orange).show()
    }

    fun showNoticeMsg(activity: Activity, noticeText: CharSequence) {
        Alerter.create(activity).setText(noticeText).setContentGravity(Gravity.START).setBackgroundColorRes(R.color.green).show()
    }
}