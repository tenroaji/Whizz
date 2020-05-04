package id.magau.whizz.ui.kelas.materi.soal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import id.magau.whizz.R
import kotlinx.android.synthetic.main.activity_analisis_hasil_latihan.*


class JawabanSoalActivity :AppCompatActivity(R.layout.activity_analisis_hasil_latihan) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindPieData(resources.getColor(R.color.colorPrimary),75f,100f-75f)
    }

    private fun bindPieData(
        mColor: Int,
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
        pieDataSet.sliceSpace = 4f
        pieDataSet.setColors(mColor, resources.getColor(R.color.colorPie))
        pieDataSet.setDrawValues(false)
        val pieData = PieData(pieDataSet)
        mChart.data = pieData
        mChart.animate()
        mChart.invalidate()
    }
}