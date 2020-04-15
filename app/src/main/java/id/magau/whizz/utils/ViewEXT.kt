package id.magau.whizz.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.text.Html
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import id.magau.whizz.R
import kotlinx.android.synthetic.main.activity_event_detail.*
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.net.URL
import java.text.NumberFormat
import java.util.*
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract



infix fun View.active (status:Boolean){
    if (status){
        setBackgroundResource(R.drawable.background_active_soal)
    }else{
        setBackgroundResource(R.drawable.background_view)
    }
}

infix fun TextView.itemChoice (status: Int) {
    if (status == 2){
        setTextColor(resources.getColor(R.color.colorPrimary))
        setBackgroundResource(R.drawable.button_choice)
    }else if (status == 1){
        setTextColor(resources.getColor(R.color.colorWhite))
        setBackgroundResource(R.drawable.button_choice_active)
    }else{
        setTextColor(resources.getColor(R.color.colorPrimary))
        setBackgroundResource(0)
    }
}

infix fun TextView.itemChoicePembahasan (status: Int) {
    if (status == 2){
        setTextColor(resources.getColor(R.color.colorFalseActive))
        setBackgroundResource(R.drawable.button_false)
    }else if (status == 1){
        setTextColor(resources.getColor(R.color.colorTrueActive))
        setBackgroundResource(R.drawable.button_true)
    }else{
        setTextColor(resources.getColor(R.color.colorEmpetyActive))
        setBackgroundResource(R.drawable.button_empety)
    }
}

infix fun TextView.itemChoicePembahasanActive(status: Int) {
    if (status == 2) {
        setBackgroundResource(R.drawable.background_tidak_lulus)
    } else if (status == 1) {
        setBackgroundResource(R.drawable.background_lulus)
    } else {
        setBackgroundResource(R.drawable.background_lewat)
    }
    setTextColor(resources.getColor(R.color.colorWhite))
}

fun ImageView?.loadImageUrl(data : String?,context: Context){
    data?.let{
        Picasso.get().load(it).into(object :Target{
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                val drawable = BitmapDrawable(context.resources,bitmap)
                this@loadImageUrl?.apply {
                    background = drawable
                    setImageResource(R.drawable.gradient_image)
                }
            }

        })
    }
}

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

//@OptIn(ExperimentalContracts::class)
//fun String?.assertNotNull() {
//    contract {
//        returns() implies (this@assertNotNull != null)
//    }
//    if (this == null){
//        throw AssertionError()
//    }
//}


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

infix fun ImageView.radius(res : String?){
    res?.let{
        Picasso.get().load(res).transform(RoundedCornersTransformation(4,0)).into(this)
    }
}

infix fun TextView.promo(data : Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml("<strike>${rupiah(data)}</strike>", Html.FROM_HTML_MODE_COMPACT);
    } else {
        this.text = Html.fromHtml("<strike>${rupiah(data)}</strike>");
    }
}

infix fun ImageView.load(res : String?){
    res?.let{
        Picasso.get().load(res).into(this)
    }
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
    return numberFormat.format(number.toDouble()).toString().replace("Rp","Rp ")
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

infix fun String.saveTo(path: String) {
    URL(this).openStream().use { input ->
        FileOutputStream(File(path)).use { output ->
            input.copyTo(output)
        }
    }
}

