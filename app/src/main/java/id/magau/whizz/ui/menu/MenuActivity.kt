package id.magau.whizz.ui.menu

import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import id.magau.whizz.R
import id.magau.whizz.ui.daftar.DaftarActivity
import id.magau.whizz.ui.login.LoginActivity
import id.magau.whizz.ui.main_menu.MainMenuActivity
import id.magau.whizz.utils.*
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_LOGIN
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : BaseActivity(R.color.colorWhite,R.layout.activity_menu) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnDaftar.setOnClickListener {
            startActivity(Intent(this@MenuActivity, DaftarActivity::class.java))
        }

        btnLogin.setOnClickListener {
            val session = SessionUtils.getInstance(this)
            val login = session.getData(PREF_KEY_LOGIN,false)
            if (login){
                startActivity(Intent(this, MainMenuActivity::class.java).run {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)})
            }else {
                startActivity(Intent(this@MenuActivity, LoginActivity::class.java))
            }
        }
    }

}
