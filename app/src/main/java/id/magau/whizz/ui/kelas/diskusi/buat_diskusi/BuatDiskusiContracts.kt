package id.magau.whizz.ui.kelas.diskusi.buat_diskusi

import id.magau.whizz.data.model.ModelPembayaran
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface BuatDiskusiContracts {

    interface Presenter : BasePresenter {
        fun sendData(idProduct: String, comment : String)
    }

    interface View : BaseView<Presenter> {
        fun doneComment()

        fun showLoading(show : Boolean)

        fun showToast(data : String)

        fun showError(code : Int, message : String?)
    }
}