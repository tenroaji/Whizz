package id.magau.whizz.ui.event_detail

import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface EventDetailContracts {

    interface Presenter : BasePresenter {
        fun loadEvent(idEvent : String)

    }

    interface View : BaseView<Presenter> {

        fun showEvent(data : ModelEvents?)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)

        fun showNoData()
    }
}