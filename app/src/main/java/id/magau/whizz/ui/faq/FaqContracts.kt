package id.magau.whizz.ui.faq

import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface FaqContracts {

    interface Presenter : BasePresenter {
        fun loadFAQ(idProduct: String)
    }

    interface View : BaseView<Presenter> {
        fun showFAQ(data : ArrayList<ModelFAQ?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int, message : String?)

    }
}