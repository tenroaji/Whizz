package id.magau.whizz.ui.products

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface ProductsContracts {

    interface Presenter : BasePresenter {
        fun loadSkillSaya()
        fun loadKelasSaya()
        fun loadEventSaya()

    }

    interface View : BaseView<Presenter> {

        fun showSkillSaya(data : ArrayList<ModelProducts?>)
        fun showKelasSaya(data : ArrayList<ModelProducts?>)
        fun showEvent(data : ArrayList<ModelEvents?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}