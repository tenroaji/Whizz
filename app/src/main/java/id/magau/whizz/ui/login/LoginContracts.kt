package id.magau.whizz.ui.login

import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface LoginContracts {

    interface Presenter : BasePresenter {
        fun sendLogin(username : String, password : String)
    }

    interface View : BaseView<Presenter> {
        fun showLoading(show : Boolean)

        fun showError(code : Int, message : String?)

        fun openMain()
    }
}