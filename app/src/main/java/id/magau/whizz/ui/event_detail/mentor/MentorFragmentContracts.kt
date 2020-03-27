package id.magau.whizz.ui.event_detail.mentor

import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelMentor
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.data.model.ModelSkills
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

        fun showMentor(data : ArrayList<ModelMentor?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}