package id.magau.whizz.ui.event_detail.mentor

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface MentorFragmentContracts {

    interface Presenter : BasePresenter {
        fun loadMentor()

    }

    interface View : BaseView<Presenter> {

        fun showMentor(data : List<ModelPemateri?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}