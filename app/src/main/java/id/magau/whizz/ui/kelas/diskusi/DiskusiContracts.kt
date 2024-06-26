package id.magau.whizz.ui.kelas.diskusi

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface DiskusiContracts {

    interface Presenter : BasePresenter {
        fun loadData(idProduct : String)
    }

    interface View : BaseView<Presenter> {

        fun showData(data : ArrayList<ModelComments?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}