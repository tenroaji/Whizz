package id.magau.whizz.ui.skill_detail.pratinjau

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface PratinjauContracts {

    interface Presenter : BasePresenter {
        fun loadData()

        fun loadFAQ()

        fun loadTestimoni()

    }

    interface View : BaseView<Presenter> {

        fun showData(data : ArrayList<ModelFile?>)

        fun showFAQ(data : ArrayList<ModelFAQ?>)

        fun showTestimoni(data : ArrayList<ModelTestimoni?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}