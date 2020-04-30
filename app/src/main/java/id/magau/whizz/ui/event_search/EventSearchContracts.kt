package id.magau.whizz.ui.event_search

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface EventSearchContracts {

    interface Presenter : BasePresenter {

        fun searchEvent(event : String?)

        fun isCanNextPage() : Boolean

        fun forceUpdate()

    }

    interface View : BaseView<Presenter> {

        fun showData(data : ArrayList<ModelEvents?>)

        fun showNextLoading(show : Boolean)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)

        fun showNoData()
    }
}