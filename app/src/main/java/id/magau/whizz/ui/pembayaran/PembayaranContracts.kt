package id.magau.whizz.ui.pembayaran

import id.magau.whizz.data.model.ModelPembayaran
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface PembayaranContracts {

    interface Presenter : BasePresenter {
        fun loadData()
    }

    interface View : BaseView<Presenter> {
        fun showData(data : ArrayList<ModelPembayaran?>)

        fun showLoading(show : Boolean)

        fun showError(code : Int, message : String?)
    }
}