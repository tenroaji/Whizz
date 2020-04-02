package id.magau.whizz.ui

import android.content.Intent
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import id.magau.whizz.R
import id.magau.whizz.ui.media_player.MediaPlayerActivity
import id.magau.whizz.ui.menu.MenuActivity
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.start
import kotlinx.android.synthetic.main.splash_screen.*

/**
 * Created by Andi Tenroaji Ahmad on 2/28/2020.
 */

class SplasScreen : BaseActivity(R.color.colorWhite,R.layout.splash_screen) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        logoText.animate().run {
            duration = 3000
            interpolator = LinearInterpolator()
            alpha(1f)
            withEndAction {
                finish()
                startActivity(Intent(this@SplasScreen, MenuActivity::class.java))
//                start(MediaPlayerActivity::class.java)
            }
        }
    }


}