package id.magau.whizz.ui.event

import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface EventContracts {

    interface Presenter : BasePresenter {
        fun loadEvent()

        fun isCanNextPage() : Boolean

        fun forceUpdate()
    }

    interface View : BaseView<Presenter> {

        fun showEvent(data : ArrayList<ModelEvent>)

        fun showLoading(show : Boolean)

        fun showNextLoading(show : Boolean)

        fun showError(code : Int?, message : String?)

        fun showNoData()
    }
}