package id.magau.whizz.utils

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ScaleXSpan
import android.util.AttributeSet

/**
 * Created by Andi Tenroaji Ahmad on 3/3/2020.
 */


class LetterSpacingTextView : androidx.appcompat.widget.AppCompatTextView {
    private var spacing = Spacing.NORMAL
    private var originalText: CharSequence? = ""

    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {}

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyle: Int
    ) : super(context, attrs, defStyle) {}

    fun getSpacing(): Float {
        return spacing
    }

    fun setSpacing(spacing: Float) {
        this.spacing = spacing
        applySpacing()
    }

    override fun setText(text: CharSequence?, type: BufferType?) {
        originalText = text
        applySpacing()
    }

    override fun getText(): CharSequence? {
        return originalText
    }

    private fun applySpacing() {
        if (originalText == null) return
        val builder = StringBuilder()
        for (i in originalText!!.indices) {
            builder.append(originalText!![i])
            if (i + 1 < originalText!!.length) {
                builder.append("\u00A0")
            }
        }
        val finalText = SpannableString(builder.toString())
        if (builder.toString().length > 1) {
            var i = 1
            while (i < builder.toString().length) {
                finalText.setSpan(
                    ScaleXSpan((spacing + 1) / 10),
                    i,
                    i + 1,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                i += 2
            }
        }
        super.setText(finalText, BufferType.SPANNABLE)
    }

    object Spacing {
        const val NORMAL = 0f
    }
}