package id.magau.whizz.ui.kelas.ujian.panel.riwayat

import id.magau.whizz.data.model.ModelRiwayat
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface RiwayatContracts {

    interface Presenter : BasePresenter {
        fun getRiwayat(idProduct: String)

        fun forceUpdate()

        fun isCanNextPage() : Boolean
    }

    interface View : BaseView<Presenter> {
        fun showRiwayat(data : MutableList<ModelRiwayat?>)

        fun showLoading(show : Boolean)

        fun showNextLoading(show : Boolean)

        fun showNoData()

        fun showError(code : Int, message : String?)

        fun showToast(message : String?)

    }
}