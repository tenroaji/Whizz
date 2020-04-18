package id.magau.whizz.ui.kelas.materi.soal

import id.magau.whizz.data.model.ModelHistoriJawaban
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 9/26/2019.
 */

interface SoalContracts {
    interface Presenter : BasePresenter {

        fun getSoal()

        fun getPembahasan(uuid:String)

        fun sendFinishSoal()

        fun forceUpdate()

        fun kumpulSoal()

    }

    interface View : BaseView<Presenter> {
        fun setUUID(UUID : String)

        fun showPembahasan()

        fun showAnalisis()

        fun showClickAgain()

        fun showLoading(show : Boolean)

        fun showSoal(data : MutableList<ModelHistoriJawaban?>)

        fun showDuration(duration : Long)

        fun showNoData(message : String)

        fun showError(code : Int, message : String?)

        fun showToast(message : String)

    }
}