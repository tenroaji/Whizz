package id.magau.whizz.ui.products

import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEventSaya
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.data.model.ModelSkills
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

        fun showSkillSaya(data : ArrayList<ModelSkills>)
        fun showKelasSaya(data : ArrayList<ModelSkills>)
        fun showEvent(data : ArrayList<ModelEventSaya>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}