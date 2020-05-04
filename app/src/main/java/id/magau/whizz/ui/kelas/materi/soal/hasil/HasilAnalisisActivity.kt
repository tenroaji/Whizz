package id.magau.whizz.ui.kelas.materi.soal.hasil

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelHasilAnalisis
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_analisis_hasil_latihan.*
import kotlinx.android.synthetic.main.item_loading.*


class HasilAnalisisActivity :BaseActivity(layout=R.layout.activity_analisis_hasil_latihan),HasilAnalisisContracts.View {
    companion object {
        const val KEY_UUID = "UUID"
    }

    private val uuidSoal by lazy {
        intent.getStringExtra(KEY_UUID)
    }

    private lateinit var mPresenter: HasilAnalisisContracts.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HasilAnalisisPresenter(this, this)
        mPresenter.getData(uuidSoal)
//        bindPieData(75f,100f-75f)
    }

    private fun bindPieData(
        percent: Float,
        offset: Float
    ) {
        mChart.setDrawEntryLabels(false)
        mChart.legend.isEnabled = false
        mChart.isDrawHoleEnabled = false
        mChart.setTouchEnabled(false)
        mChart.description = null
        val entryAbsen = PieEntry(percent)
        val entryEmpty = PieEntry(offset)
        val mEntries: ArrayList<PieEntry> = arrayListOf()
        mEntries.add(0, entryAbsen)
        mEntries.add(1, entryEmpty)
        val pieDataSet = PieDataSet(mEntries, "Test")
//        pieDataSet.sliceSpace = 4f
        pieDataSet.setColors(resources.getColor(R.color.colorPrimary), resources.getColor(R.color.colorPie))
        pieDataSet.setDrawValues(false)
        val pieData = PieData(pieDataSet)
        mChart.data = pieData
        mChart.animate()
        mChart.invalidate()
    }

    override fun showData(data: ModelHasilAnalisis) {
        tvKelulusan.text = data.ket
        tvSkorBenar.text = data.jumlah_benar.toString()
        tvSkorLewat.text = data.jumlah_dilewati.toString()
        tvSkorSalah.text = data.jumlah_salah.toString()
        tvSkorPercent.text = "${data.skors}%"
        bindPieData(data.skors!!.toFloat(),100-data.skors!!.toFloat())
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: HasilAnalisisContracts.Presenter) {
        mPresenter = presenter
    }
}