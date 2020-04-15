package id.magau.whizz.ui.kelas.materi

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface MateriContracts {

    interface Presenter : BasePresenter {
        fun loadData(idProduct : String)

    }

    interface View : BaseView<Presenter> {

        fun showData(data : List<SectionModel?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}