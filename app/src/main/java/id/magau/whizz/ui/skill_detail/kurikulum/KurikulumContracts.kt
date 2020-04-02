package id.magau.whizz.ui.skill_detail.kurikulum

import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelKurikulum
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface KurikulumContracts {

    interface Presenter : BasePresenter {
        fun loadData(idProduct : String)

    }

    interface View : BaseView<Presenter> {

        fun showData(data : ArrayList<ModelKurikulum?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}