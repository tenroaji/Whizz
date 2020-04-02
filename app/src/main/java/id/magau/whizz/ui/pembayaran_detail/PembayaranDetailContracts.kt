package id.magau.whizz.ui.pembayaran_detail

import id.magau.whizz.data.model.ModelPembayaran
import id.magau.whizz.data.model.ModelPembayaranDetail
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface PembayaranDetailContracts {

    interface Presenter : BasePresenter {
        fun loadData(idProduct:String, kodeBank : String)
    }

    interface View : BaseView<Presenter> {
        fun showData(data : ModelPembayaranDetail)

        fun showLoading(show : Boolean)

        fun showError(code : Int, message : String?)
    }
}