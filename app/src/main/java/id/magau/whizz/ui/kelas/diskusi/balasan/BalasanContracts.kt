package id.magau.whizz.ui.kelas.diskusi.balasan

import id.magau.whizz.data.model.ModelReplys
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface BalasanContracts {

    interface Presenter : BasePresenter {
        fun loadData(idComment: String)

        fun sendReplys(reply: String, idComment : String)
    }

    interface View : BaseView<Presenter> {
        fun showData (data : ArrayList<ModelReplys?>)

        fun showToast(data : String)

        fun showLoading(show : Boolean)

        fun showError(code : Int, message : String?)
    }
}