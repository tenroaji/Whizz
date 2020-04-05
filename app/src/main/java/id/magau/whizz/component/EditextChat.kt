package id.magau.whizz.component

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import com.google.android.material.textfield.TextInputEditText

class EditextChat : TextInputEditText {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

//    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            val input = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//            input.hideSoftInputFromWindow(this.windowToken, 0)
//        }
//
//        return super.onKeyPreIme(keyCode, event)
//    }
}