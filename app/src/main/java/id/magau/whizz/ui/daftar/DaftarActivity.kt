package id.magau.whizz.ui.daftar

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.ArrayAdapter
import id.magau.whizz.R
import id.magau.whizz.ui.login.LoginActivity
import id.magau.whizz.ui.main_menu.MainMenuActivity
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_daftar.*
import kotlinx.android.synthetic.main.item_loading.*

/**
 * Created by Andi Tenroaji Ahmad on 2/29/2020.
 */

class DaftarActivity : BaseActivity(R.color.colorWhite,R.layout.activity_daftar), DaftarContracts.View {
    private lateinit var mPresenter : DaftarContracts.Presenter
    private var isDropdownShowing = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaftarPresenter(this,this)

        val jenisKelamin =
            arrayOf<String?>("Laki-Laki", "Perempuan")

        val adapter: ArrayAdapter<String?> = ArrayAdapter(
            this,
            R.layout.dropdown_jenis_kelamin,
            jenisKelamin
        )

        dropdownJenisKelamin.setText("Laki-Laki")
        dropdownJenisKelamin.setAdapter(adapter)
        dropdownJenisKelamin.setOnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                if (isDropdownShowing) {
                    dropdownJenisKelamin.dismissDropDown()
                    isDropdownShowing = false
                } else {
                    dropdownJenisKelamin.showDropDown()
                    isDropdownShowing = true
                }
                return@setOnTouchListener true
            }
            return@setOnTouchListener false
        }


        tvMasuk.ripple().setOnClickListener {
            start(LoginActivity::class.java)

        }

        btnDaftar.setOnClickListener {
            mPresenter.sendRegister(
                editNama.text.toString(),
                editNamaPengguna.text.toString(),
                editNomorKTP.text.toString(),
                editEmail.text.toString(),
                editNomorTelepon.text.toString(),
                dropdownJenisKelamin.text.toString(),
                editAlamat.text.toString(),
                editPassword.text.toString()
            )
        }

    }

    override fun showRegis() {
        startActivity(Intent(this@DaftarActivity, MainMenuActivity::class.java).run {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)})
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: DaftarContracts.Presenter) {
        mPresenter = presenter
    }


}