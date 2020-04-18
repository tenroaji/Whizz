package id.magau.whizz.ui.soal

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelHistoriJawaban
import id.magau.whizz.ui.soal.detail_soal.DetailSoalFragment
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.enable
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_soal.*
import kotlinx.android.synthetic.main.item_custom_dialog_done.*
import kotlinx.android.synthetic.main.item_custom_dialog_done.view.*


/**
 * Created by Andi Tenroaji Ahmad on 12/23/2019.
 */

class SoalActivity : BaseActivity(layout=R.layout.activity_soal), SoalContracts.View {
companion object {
    const val KEY_JENIS = "JENIS"
    const val KEY_UUID = "UUID"
    const val KEY_DATA_SOAL = "DATA_SOAL"
}

    private lateinit var mPresenter: SoalContracts.Presenter
    private var mAdapterSoal = AdapterNomorSoal()
    private var dataSoal: MutableList<ModelHistoriJawaban?> = mutableListOf()
    private var mSoal = true
    var mCachePosition = -1
    var mCurrentPosition = 0
    var usedTime = 0L
    private var mUuid = ""
//    private var mDataSoal by lazy {
////        intent.getSerializableExtra(KEY_DATA_SOAL)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SoalPresenter(this,this)
        mUuid = intent.getStringExtra(KEY_UUID)?:""
        mSoal = intent.getBooleanExtra(KEY_JENIS,true)
//
//        if(mSoal){
//            mPresenter.start()
//        }else {
//            mPresenter.getPembahasan(mUuid)
//            tvTitleToolbar.text = resources.getString(R.string.label_pembahasan)
//        }


        mRecyclerButtonSoal.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        mRecyclerButtonSoal.adapter = mAdapterSoal

        mAdapterSoal.setOnChangeSoalListener(object : AdapterNomorSoal.OnChangeSoalListener {
            override fun onChange(view: View, position: Int) {
                val isNext = position >= mCurrentPosition
                mCurrentPosition = position
                replaceFragment(position, isNext)

                if (position <= 0) {
                    btnPrev enable false
                } else {
                    btnPrev enable true
                }

                if (mSoal) {
                    if (position >= mAdapterSoal.itemCount - 1) {
                        btnNext.text = getString(R.string.label_kumpulkan)
                    } else {
                        btnNext.text = getString(R.string.label_next)
                    }
                } else {
                    if (position >= mAdapterSoal.itemCount - 1) {
                        btnNext enable false
                    } else {
                        btnNext enable true
                    }
                }

            }

        })

        btnNext.setOnClickListener {
            validasiNext()
        }

        btnPrev.setOnClickListener {
            validasiPrev()
        }

        tvAkhiriLatihan.setOnClickListener {
            openDialogDone()
        }
    }

    private fun replaceFragment(pos: Int, isNext: Boolean) {
        if (pos != mCachePosition) {
            supportFragmentManager.beginTransaction().apply {
                if (isNext) {
                    setCustomAnimations(R.anim.slide_from_right, R.anim.slide_left)
                } else {
                    setCustomAnimations(R.anim.left_to_right, R.anim.right_to_left)
                }
                replace(
                    R.id.mFragmentContainer, DetailSoalFragment.newInstance(
                        pos.toString(),
                        dataSoal[pos]!!,
                        mSoal
                    )
                )
                commit()
            }

            mAdapterSoal.setActivePosition(mCurrentPosition,mSoal)
            mRecyclerButtonSoal.smoothScrollToPosition(pos)
        }
        mCachePosition = mCurrentPosition
    }

    private fun validasiNext() {

        if (btnNext.text == getString(R.string.label_kumpulkan)) {
            openDialogDone()
        }
        if (mCurrentPosition < mAdapterSoal.itemCount - 1) {
            mCurrentPosition += 1
            replaceFragment(mCurrentPosition, true)
        }
        if (mSoal) {
            if (mCurrentPosition >= mAdapterSoal.itemCount - 1) {
                btnNext.text = getString(R.string.label_kumpulkan)
            }
        } else {
            if (mCurrentPosition >= mAdapterSoal.itemCount - 1) {
                btnNext enable false
            }
        }

        if (!btnPrev.isEnabled) {
            btnPrev enable true
        }
    }

    private fun validasiPrev() {
        if (mCurrentPosition > 0) {
            mCurrentPosition -= 1
            replaceFragment(mCurrentPosition, false)
        }
        if (mCurrentPosition <= 0) {
            btnPrev enable false
        }
        if (btnNext.text == getString(R.string.label_kumpulkan)) {
            btnNext.text = getString(R.string.label_next)
        }

        if (!btnNext.isEnabled) {
            btnNext enable true
        }
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        if (fragment is DetailSoalFragment) {
            fragment.setOnChoiceSoalListener(object : DetailSoalFragment.OnChoiceSoalListener {
                override fun onChoice(
                    position: Int,
                    choice: String,
                    idSoal: String,
                    idHistory: String,
                    type: String,
                    soal: Boolean
                ) {
                    if (type.contains("twk")){
                        mPresenter.sendTWK(idSoal,choice,idHistory)
                    }else if (type.contains("tiu")){
                        mPresenter.sendTIU(idSoal,choice,idHistory)
                    }else if (type.contains("tkp")){
                        mPresenter.sendTKP(idSoal,choice,idHistory)
                    }
                    mAdapterSoal.answerPosition(position)
                    val dataUpdate = dataSoal[position]?.copy(pilihan = choice)
                    dataSoal[position] = dataUpdate
                }
            })
        }
    }


    private fun startTimer(duration: Long) {
        val timer = object : CountDownTimer(duration, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTitleToolbar.text = formatTime(millisUntilFinished.toInt())
                usedTime = duration - millisUntilFinished
            }

            override fun onFinish() {
//                openAnalisis()
            }
        }
        timer.start()
    }

    private fun formatTime(millis: Int): String {
        val seconds = millis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        return (if (hours == 0) "" else "$hours:") + String.format(
            "%02d:%02d",
            minutes % 60,
            seconds % 60
        )
    }

    private fun openDialogDone() {
        val mDialogView =
            LayoutInflater.from(this@SoalActivity).inflate(R.layout.item_custom_dialog_done, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this@SoalActivity)
            .setView(mDialogView)
        //show dialog
        val mAlertDialog = mBuilder.show()
        val empetyChoice: MutableList<Int> = mutableListOf()
        var alert = ""
        var answer = 0
        var no = 0
        for (data in dataSoal) {
            no++
            if (data?.pilihan.isNullOrEmpty()) {
                empetyChoice.add(no)
            }
            answer++
        }

        for ((data, nomor) in empetyChoice.withIndex()) {
            if (data == empetyChoice.size - 1) {
                alert += nomor
            } else {
                alert += "${nomor}, "
            }
        }

        if (empetyChoice.size < 100) {
            mAlertDialog.viewAlert visibility true
            mAlertDialog.tvAlert.text = "Anda belum mengerjakan nomor $alert"
        } else if (empetyChoice.size == 100) {
            mAlertDialog.viewAlert visibility true
            mAlertDialog.tvAlert.text = "Anda belum mengerjakan soal apapun"
        }


        mDialogView.imgClose.setOnClickListener {
            mAlertDialog.dismiss()
        }

        mDialogView.btnKumpulkan.setOnClickListener {
            mAlertDialog.dismiss()
            mPresenter.kumpulSoal()

        }
        mDialogView.btnBatal.setOnClickListener {
            mAlertDialog.dismiss()
        }
    }

    override fun showClickAgain() {

    }

    override fun showLoading(show: Boolean) {
        groupBtn visibility !show
        mLoading visibility show
    }

    override fun showSoal(data: MutableList<ModelHistoriJawaban?>) {
        dataSoal = data
        mAdapterSoal.updateAdapter(data)
        if(mSoal){
            tvAkhiriLatihan visibility true
        }
        replaceFragment(0,true)
    }

    override fun showDuration(duration: Long) {
        startTimer(duration)
    }

    override fun showAnalisis() {
//        finish()
//        startActivity(Intent(this@SoalActivity, ActivityAnalisis::class.java).apply {
//            putExtra(KEY_UUID,mUuid)
//        })
    }

    override fun setUUID(UUID: String) {
        mUuid = UUID
    }

    override fun showPembahasan() {

    }

    override fun showNoData(message: String) {

    }

    override fun showError(code: Int, message: String?) {
        Toast.makeText(this@SoalActivity, "$code , $message", Toast.LENGTH_LONG).show()
    }

    override fun showToast(message: String) {
        Toast.makeText(this@SoalActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun setPresenter(presenter: SoalContracts.Presenter) {
        mPresenter = presenter
    }
}