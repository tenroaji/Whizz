package id.magau.whizz.ui.main_menu

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface MainMenuContracts {

    interface Presenter : BasePresenter {
        fun loadData()

    }

    interface View : BaseView<Presenter> {
        fun showCount(data : Int?)
        fun showSkillPopular(data : ArrayList<ModelProducts?>)
        fun showEvents(data : ArrayList<ModelEvents?>)
        fun showPromo(data : ArrayList<ModelProducts?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}