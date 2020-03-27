package id.magau.whizz.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.text.TextUtils
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import java.io.File
import java.text.NumberFormat
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

fun getInitialName(displayName: String): String? {
    if (TextUtils.isEmpty(displayName)) {
        return null
    }
    val words = displayName.split(" ").toTypedArray()
    return if (words.size == 1) {
        words[0][0].toString()
    } else {
        words[0][0].toString() + words[words.size - 1][0].toString()
    }
}

@UseExperimental(ExperimentalContracts::class)
fun String?.assertNotNull() {
    contract {
        returns() implies (this@assertNotNull != null)
    }
    if (this == null){
        throw AssertionError()
    }
}


infix fun View.visibility(status: Boolean) {
    visibility =
        if (status)
            View.VISIBLE
        else
            View.GONE
}

infix fun View.enable(status: Boolean) {
    isEnabled = status
}

fun View.ripple(): View {
    val value = TypedValue()
    context.theme.resolveAttribute(android.R.attr.selectableItemBackground, value, true)
    setBackgroundResource(value.resourceId)
    isFocusable = true // Required for some view types
    return this
}

fun ImageView.circleRes(res : String){
    Picasso.get().load(res).transform(PicassoCircleTransform()).into(this)
}

fun Activity.toast(message : String?){
    Toast.makeText(this,message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String?){
    Toast.makeText(context,message, Toast.LENGTH_SHORT).show()
}

fun Activity.startOnTop(activity : Class<*>){
    startActivity(Intent(this, activity).run {
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)})
    overridePendingTransition(R.anim.enter, R.anim.exit)
}

fun Activity.start(activity : Class<*>){
    startActivity(Intent(this, activity))
    overridePendingTransition(R.anim.enter, R.anim.exit)

}

fun Context.start(activity: Class<*>){
    startActivity(Intent(this, activity))
    (this as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
}

fun Fragment.start(activity : Class<*>){
    startActivity(Intent(requireContext(), activity))
        (requireContext() as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
}

operator fun Context.contains(text: Any): Boolean {
    Toast.makeText(this, text.toString(), Toast.LENGTH_SHORT).show()
    return false
//    sample
//    val Breakfast = "Toasty"
//    Breakfast in this
}


infix fun String.equals(par: String) = this == par

val Int.seconds: Long get() = this * 1000L

infix fun (() -> Any).after(delay: Long) {
    Handler().postDelayed({
        this()
    }, delay)
}

 fun rupiah(number: Int): String{
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(number.toDouble()).toString()
}

infix fun (() -> Any).every(delay: Long) {
    val handler = Handler()
    val runnable = object : Runnable {
        override fun run() {
            this@every()
            handler.postDelayed(this, delay)
        }
    }
    handler.postDelayed(runnable,delay)
}

infix fun View.clicked(func: () -> Unit){
    this.setOnClickListener {
        func()
    }
}

infix fun Double.format(digits: Int) = "%.${digits}f".format(this)

