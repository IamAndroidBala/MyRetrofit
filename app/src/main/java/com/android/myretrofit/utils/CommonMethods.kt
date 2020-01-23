package com.android.myretrofit.utils

import android.content.Context
import android.net.ConnectivityManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.myretrofit.R
import com.kaopiz.kprogresshud.KProgressHUD

class CommonMethods {

    companion object {

        fun createHUD(activity: Context): KProgressHUD {
            return KProgressHUD.create(activity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(activity.resources.getString(R.string.please_wait))
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show()
        }

        fun cancelHUD(kProgressHUD: KProgressHUD) {
            if(kProgressHUD.isShowing) {
                kProgressHUD.dismiss()
            }
        }

        fun isNetworkAvailable(context: Context): Boolean {
            try {
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                return if (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isFailover) false else if (cm.activeNetworkInfo != null && cm.activeNetworkInfo.isAvailable && cm.activeNetworkInfo.isConnected) true else false
            } catch (e: Exception) { }
            return true
        }

        fun showToast(context: Context, title: String?) {
            val toast = Toast.makeText(context, title, Toast.LENGTH_SHORT)
            val view = toast.view
            val text = view.findViewById<TextView>(android.R.id.message)
            text.setTextColor(ContextCompat.getColor(context,R.color.colorWhite))
            toast.show()
        }

    }
}