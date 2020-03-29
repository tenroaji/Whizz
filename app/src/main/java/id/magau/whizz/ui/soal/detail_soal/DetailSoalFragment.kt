package id.magau.whizz.ui.soal.detail_soal

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelHistoriJawaban
import id.magau.whizz.ui.soal.AdapterNomorSoal
import id.magau.whizz.ui.soal.SoalActivity.Companion.KEY_JENIS
import id.magau.whizz.utils.active
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.fragment_soal.*
import kotlinx.android.synthetic.main.fragment_soal.view.*


class DetailSoalFragment : Fragment() {


    companion object {
        const val KEY_ID = "id"
        const val KEY_DATA = "data"

        @JvmStatic
        fun newInstance(id: String, data: ModelHistoriJawaban, soal: Boolean): DetailSoalFragment {
            val args = Bundle()
            args.putString(KEY_ID, id)
            args.putSerializable(KEY_DATA, data)
            args.putBoolean(KEY_JENIS, soal)
            val fragment = DetailSoalFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var mChoiceSoalListener: OnChoiceSoalListener? = null
    var mView: View? = null
    private var mPosition = 0
    private var mSoal = false
    private var idSoal = ""
    private var idHistory = ""
    private var mType = ""
    private lateinit var data: ModelHistoriJawaban
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_soal, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPosition = arguments?.getString(KEY_ID)!!.toInt()
        mSoal = arguments?.getBoolean(KEY_JENIS) ?: true
        data = arguments?.getSerializable(KEY_DATA) as ModelHistoriJawaban
        idSoal = data.id_soal.toString()
        idHistory = data.id.toString()
        mType = data.jenis.toString()
        view.tvSoal.text = data.soal
        if (!data.gambar_soal.isNullOrEmpty()) {
            view.imgSoal visibility true
            Picasso.get().load(data.gambar_soal).into(view.imgSoal)
        }
        if (mSoal) {
            view.tvChoiceA.text = data.a
            view.tvChoiceB.text = data.b
            view.tvChoiceC.text = data.c
            view.tvChoiceD.text = data.d
            view.tvChoiceE.text = data.e
            data.pilihan?.let {
                choicer(view, it)
            }
            if (!data.gambar_a.isNullOrEmpty()) {
                groupChoiceImage visibility true
                Picasso.get().load(data.gambar_a).into(imgA)
                Picasso.get().load(data.gambar_b).into(imgB)
                Picasso.get().load(data.gambar_c).into(imgC)
                Picasso.get().load(data.gambar_d).into(imgD)
                Picasso.get().load(data.gambar_e).into(imgE)
            }
        } else {
            view.groupPembahasan visibility true
            view.groupChoice visibility false

            if (data.bobot == 5) {
                imgJawabanAnda.setImageResource(R.drawable.ic_jawabanbenar)
                tvChoiceJawabanAnda.text = "${data.jawabanDiPilih?.urutan?.toUpperCase()}."
                tvJawabanAnda.text = data.jawabanDiPilih?.jawaban
                if (!data.jawabanDiPilih?.gambar.isNullOrEmpty()) {
                    gambarJawabanAnda visibility true
                    Picasso.get().load(data.jawabanDiPilih?.gambar).into(gambarJawabanAnda)
                }
            } else {
                view.groupJawabanBenar visibility true
                if (data.pilihan.isNullOrEmpty()) {
                    viewJawabanAnda.setBackgroundResource(R.drawable.button_empety)
                    tvChoiceJawabanAnda.text = "Anda tidak menjawab soal ini"
                    imgJawabanAnda visibility false

                } else {
                    tvChoiceJawabanAnda.text = "${data.jawabanDiPilih?.urutan?.toUpperCase()}."
                    tvJawabanAnda.text = data.jawabanDiPilih?.jawaban
                    if (!data.jawabanDiPilih?.gambar.isNullOrEmpty()) {
                        gambarJawabanAnda visibility true
                        Picasso.get().load(data.jawabanDiPilih?.gambar).into(gambarJawabanAnda)
                    }
                }
                tvChoiceJawabanBenar.text = "${data.jawaban?.urutan?.toUpperCase()}."
                tvJawabanBenar.text = data.jawaban?.jawaban
                if (!data.jawaban?.gambar.isNullOrEmpty()) {
                    gambarJawabanBenar visibility true
                    Picasso.get().load(data.jawabanDiPilih?.gambar).into(gambarJawabanBenar)
                }
            }
            tvPembahasan.text = data.pembahasan
            if (!data.gambarPembahasan.isNullOrEmpty()) {
                imgPembahasan visibility true
                Picasso.get().load(data.jawabanDiPilih?.gambar).into(gambarJawabanBenar)
            }
        }
        val mAdapter = AdapterNomorSoal()
        mAdapter.setOnChangeSoalListener(object :
            AdapterNomorSoal.OnChangeSoalListener {
            override fun onChange(view: View, position: Int) {
                mView = view
            }

        })

        view.choiceA.setOnClickListener {
            choicer(view, it)
            mChoiceSoalListener?.onChoice(mPosition, "A", idSoal, idHistory, mType, true)
        }
        view.choiceB.setOnClickListener {
            choicer(view, it)
            mChoiceSoalListener?.onChoice(mPosition, "B", idSoal, idHistory, mType, true)
        }
        view.choiceC.setOnClickListener {
            choicer(view, it)
            mChoiceSoalListener?.onChoice(mPosition, "C", idSoal, idHistory, mType, true)
        }
        view.choiceD.setOnClickListener {
            choicer(view, it)
            mChoiceSoalListener?.onChoice(mPosition, "D", idSoal, idHistory, mType, true)
        }
        view.choiceE.setOnClickListener {
            choicer(view, it)
            mChoiceSoalListener?.onChoice(mPosition, "E", idSoal, idHistory, mType, true)
        }

    }

    private fun choicer(root: View, pilihan: String) {
        val choicer = mutableListOf("a", "b", "c", "d", "e")
        val viewChoicer = mutableListOf(
            root.choiceA,
            root.choiceB,
            root.choiceC,
            root.choiceD,
            root.choiceE
        )
        for ((pos, choice) in choicer.withIndex()) {
            if (choice.contains(pilihan, true)) {
                viewChoicer[pos].setBackgroundResource(R.drawable.background_active_soal)
                mChoiceSoalListener?.onChoice(mPosition, choice, idSoal, idHistory, mType, true)
            }
        }
    }

    private fun choicer(root: View, pilihan: View) {
        val choicer = mutableListOf(
            root.choiceA,
            root.choiceB,
            root.choiceC,
            root.choiceD,
            root.choiceE
        )
        for (button in choicer) {
            if (button == pilihan) {
                button active true
            } else {
                button active false
            }
        }
    }


    fun setOnChoiceSoalListener(listener: OnChoiceSoalListener) {
        mChoiceSoalListener = listener
    }

    interface OnChoiceSoalListener {
        fun onChoice(
            position: Int,
            choice: String,
            idSoal: String,
            idHistory: String,
            type: String,
            soal: Boolean
        )
    }

}
