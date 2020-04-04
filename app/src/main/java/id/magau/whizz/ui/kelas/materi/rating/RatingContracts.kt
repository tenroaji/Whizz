package id.magau.whizz.ui.kelas.materi.rating

import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface RatingContracts {

    interface Presenter : BasePresenter {
        fun loadData(idProduct: String, rating : Int)
    }

    interface View : BaseView<Presenter> {
        fun showToast(data : String)

        fun showLoading(show : Boolean)

        fun showError(code : Int, message : String?)
    }
}