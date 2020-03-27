package id.magau.whizz.ui.kelas.file

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface FileContracts {

    interface Presenter : BasePresenter {
        fun showData()

    }

    interface View : BaseView<Presenter> {

        fun showData(data : ArrayList<ModelFile?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}