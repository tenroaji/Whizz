package id.magau.whizz.ui.kelas.materi.soal.hasil

import id.magau.whizz.data.model.*
import id.magau.whizz.utils.BasePresenter
import id.magau.whizz.utils.BaseView

/**
 * Created by Andi Tenroaji Ahmad on 12/18/2019.
 */

interface HasilAnalisisContracts {

    interface Presenter : BasePresenter {
        fun getData(uuidSoal:String)

    }

    interface View : BaseView<Presenter> {

        fun showData(data : ModelHasilAnalisis)

        fun showLoading(show : Boolean)

        fun showError(code : Int?, message : String?)
    }
}