package id.magau.whizz.ui.daftar

import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface DaftarContracts {

    interface Presenter : BasePresenter {
        fun sendRegister(nama : String,
                           user : String,
                           ktp : String,
                           email : String,
                           hp : String,
                           jk : String,
                           alamat : String,
                           password : String)

    }

    interface View : BaseView<Presenter> {

        fun showRegis()

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}