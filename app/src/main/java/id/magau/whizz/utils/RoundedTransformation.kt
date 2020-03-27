package id.magau.whizz.utils

import android.graphics.*
import android.os.Build
import com.squareup.picasso.Transformation

/**
 * Created by Andi Tenroaji Ahmad on 3/16/2020.
 */

class RoundedTransformation(private val radius: Float? = null, private val margin: Float = 0f) :
    Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val paint = Paint().apply {
            isAntiAlias = true
            shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        }
        val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Canvas(output).drawRoundRect(margin, margin, source.width - margin, source.height - margin,
                radius ?: source.width.toFloat() / 2, radius ?: source.height.toFloat() / 2,
                paint)
        }
        if (source != output) {
            source.recycle()
        }
        return output
    }

    override fun key(): String {
        return "rounded(radius=$radius, margin=$margin)"
    }
}