package id.magau.whizz.ui.kelas

import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface KelasContracts {

    interface Presenter : BasePresenter {
        fun loadData(idProduct:String)

        fun loadMySkill(idProduct: String)

    }

    interface View : BaseView<Presenter> {

        fun showSkill(data : ModelProducts?)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}