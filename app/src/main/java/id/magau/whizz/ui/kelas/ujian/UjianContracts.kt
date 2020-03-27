package id.magau.whizz.ui.kelas.ujian

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface UjianContracts {

    interface Presenter : BasePresenter {
        fun showData()

    }

    interface View : BaseView<Presenter> {

        fun showData(data : ArrayList<ModelUjian?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}