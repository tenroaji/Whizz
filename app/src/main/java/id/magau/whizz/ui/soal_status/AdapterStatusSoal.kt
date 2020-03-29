package id.magau.whizz.ui.soal_status

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.History
import id.magau.whizz.utils.itemChoicePembahasanActive
import kotlinx.android.synthetic.main.item_list_status_soal.view.*


/**
 * Created by Andi Tenroaji Ahmad on 10/15/2019.
 */
class AdapterStatusSoal :
    RecyclerView.Adapter<AdapterStatusSoal.ViewHolder>() {
    private var data: MutableList<History>? = mutableListOf()
    private var no = MutableList(101) { i -> i + 1 }
    private var mWidth = 0
    private var mHeight = 0
    fun updateAdapter(datax: List<History>, width: Int, height: Int) {
        data?.clear()
        data?.addAll(datax)
        mWidth = width
        mHeight = height
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        LayoutInflater.from(parent.context).inflate(R.layout.item_list_status_soal, parent, false)
            .also {
                return ViewHolder(it)
            }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {


            val layoutParams = holder.itemView.layoutParams as RecyclerView.LayoutParams
            layoutParams.apply {
                width = mWidth
                height = mHeight
                bottomMargin =
                    holder.itemView.context.resources.getDimensionPixelSize(R.dimen.margin_8dp)
            }
            holder.itemView.layoutParams = layoutParams
            tvNomorSoal.text = "${no[position]}"
            val bobot = data?.get(position)?.bobot
            val pilihan = data?.get(position)?.pilihan
            if (pilihan.isNullOrEmpty()) {
                tvNomorSoal itemChoicePembahasanActive 0
            } else if (bobot == 5) {
                tvNomorSoal itemChoicePembahasanActive 1
            } else {
                tvNomorSoal itemChoicePembahasanActive 2
            }
        }

    }

    override fun getItemCount(): Int {
        return data!!.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}