package id.magau.whizz.ui.kelas.materi.soal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelHistoriJawaban
import id.magau.whizz.utils.itemChoice
import id.magau.whizz.utils.itemChoicePembahasan
import id.magau.whizz.utils.itemChoicePembahasanActive
import kotlinx.android.synthetic.main.item_list_status_soal.view.*


/**
 * Created by Andi Tenroaji Ahmad on 10/15/2019.
 */
class AdapterNomorSoal :
    RecyclerView.Adapter<AdapterNomorSoal.ViewHolder>() {
    private var data: MutableList<ModelHistoriJawaban>? = mutableListOf()
    private var onChoice: MutableList<Int> = mutableListOf()
    private var mChangeSoalListener: OnChangeSoalListener? = null
    private var mActivePosition = 0
    private var mSoal = true

    fun updateAdapter(datax: MutableList<ModelHistoriJawaban>?) {
        data?.clear()
        data = datax
        notifyDataSetChanged()
    }

    fun setActivePosition(position: Int, soal: Boolean) {
        val tempActive = mActivePosition
        mSoal = soal
        mActivePosition = position
        notifyItemChanged(mActivePosition)
        if (tempActive != position) {
            notifyItemChanged(tempActive)
        }
    }

    fun answerPosition(position: Int) {
        data?.get(position)?.active = true
//        onChoice.add(position)
        notifyItemChanged(position)
    }


    fun clearList() {
        data?.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterNomorSoal.ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_status_soal, parent, false)
            .also {
                return ViewHolder(it)
            }
    }


    override fun onBindViewHolder(holder: AdapterNomorSoal.ViewHolder, position: Int) {
        holder.itemView.run {
            val layoutParams = RecyclerView.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.size34dp),
                resources.getDimensionPixelSize(R.dimen.size34dp)
            )
            layoutParams.marginEnd =
                holder.itemView.context.resources.getDimensionPixelSize(R.dimen.margin_8dp)
            if (position == 0) {
                layoutParams.marginStart =
                    holder.itemView.context.resources.getDimensionPixelSize(R.dimen.margin_24dp)
            } else if (position == itemCount - 1) {
                layoutParams.marginEnd =
                    holder.itemView.context.resources.getDimensionPixelSize(R.dimen.margin_24dp)
            }
            holder.itemView.layoutParams = layoutParams

            tvNomorSoal.text = "${position+1}"
            val isActive = data?.get(position)?.active ?: false
            val bobot = data?.get(position)?.bobot
            val pilihan = data?.get(position)?.pilihan
            val jawaban = data?.get(position)?.jawaban?.urutan
            if (mSoal) {
                if (mActivePosition == position) {
                    tvNomorSoal itemChoice 1
                } else if (isActive && mActivePosition != position || data?.get(position)?.pilihan != null) {
                    tvNomorSoal itemChoice 2
                } else {
                    tvNomorSoal itemChoice 0
                }
            } else {
                if (mActivePosition == position && pilihan.isNullOrEmpty()){
                    tvNomorSoal itemChoicePembahasanActive 0
                }else if (isActive && mActivePosition != position && pilihan.isNullOrEmpty()){
                    tvNomorSoal itemChoicePembahasan 0
                }else if (pilihan.isNullOrEmpty()){
                    tvNomorSoal itemChoicePembahasan 0
                }
                else if (mActivePosition == position && bobot == 5){
                    tvNomorSoal itemChoicePembahasanActive 1
                }else if (isActive && mActivePosition != position && bobot == 5){
                    tvNomorSoal itemChoicePembahasan 1
                }else if (bobot == 5){
                    tvNomorSoal itemChoicePembahasan 1
                }
                else if (mActivePosition == position && bobot != 5){
                    tvNomorSoal itemChoicePembahasanActive 2
                }else if (isActive && mActivePosition != position && bobot!=5){
                    tvNomorSoal itemChoicePembahasan 2
                }else {
                    tvNomorSoal itemChoicePembahasan 2
                }
                }


            val isCollapsed = onChoice.contains(position)

            setOnClickListener {
                mChangeSoalListener?.onChange(it, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setOnChangeSoalListener(listener: OnChangeSoalListener) {
        mChangeSoalListener = listener
    }

    interface OnChangeSoalListener {
        fun onChange(view: View, position: Int)
    }


}