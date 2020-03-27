package id.magau.whizz.utils

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import id.magau.whizz.R

/**
 * Created by Andi Tenroaji Ahmad on 3/6/2020.
 */

open class BaseActivity(val color: Int? = R.color.colorWhite,layout : Int) : AppCompatActivity(layout) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && color != null) {
//            window.apply {
//                val winParams = attributes.apply {
////                    flags = flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
//                    flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
//                }
//                attributes = winParams
//                statusBarColor = ContextCompat.getColor(context, color)
//            }
//        }
//        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val view = window.decorView
//            var flag = view.systemUiVisibility
//            flag = flag xor View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
//            view.systemUiVisibility = flag
//        }
//        else {
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//        }

        findViewById<Toolbar>(R.id.mToolbar)?.setNavigationOnClickListener {
            onBackPressed()
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
    }
}