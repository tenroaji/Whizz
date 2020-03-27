package id.magau.whizz.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelLogin
import id.magau.whizz.ui.daftar.DaftarActivity
import id.magau.whizz.ui.main_menu.MainMenuActivity
import id.magau.whizz.utils.*
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_EMAIL
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_PASSWORD
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_REMEMBER
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.item_loading.*

class LoginActivity : BaseActivity(R.color.colorWhite,R.layout.activity_login),LoginContracts.View{
    private lateinit var mPresenter : LoginContracts.Presenter

    private lateinit var mSession: SessionUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LoginPresenter(this,this)
        mSession = SessionUtils(this)

        tvIngatSaya.setOnClickListener {
            checkIngat.isChecked = !checkIngat.isChecked
        }
        btnMasuk.setOnClickListener {
            if (checkIngat.isChecked){
                mSession.editData(PREF_KEY_EMAIL,editEmail.text.toString())
                mSession.editData(PREF_KEY_PASSWORD,editPassword.text.toString())
                mSession.editData(PREF_KEY_REMEMBER,true)
            }else {
                mSession.editData(PREF_KEY_REMEMBER,false)
            }
            mPresenter.sendLogin(editEmail.text.toString(),editPassword.text.toString())
        }

        tvDaftar.ripple().setOnClickListener {
            start(DaftarActivity::class.java)
        }


    }

    override fun onResume() {
        super.onResume()

        if (mSession.getData(PREF_KEY_REMEMBER,false) as Boolean){
            editEmail.setText(mSession.getData(PREF_KEY_EMAIL,"") as String)
            editPassword.setText(mSession.getData(PREF_KEY_PASSWORD,"") as String)
            checkIngat.isChecked = true
        }
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int, message: String?) {
        toast("$code -> $message")
    }

    override fun openMain() {
        startActivity(Intent(this@LoginActivity, MainMenuActivity::class.java).run {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)})
    }

    override fun setPresenter(presenter: LoginContracts.Presenter) {
        mPresenter = presenter
    }
}

