package id.magau.whizz.ui.kelas.materi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelSubSectionMateri
import kotlinx.android.synthetic.main.item_list_materi.view.tvTitle
import kotlinx.android.synthetic.main.item_list_sub_materi.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterSubMateri : RecyclerView.Adapter<AdapterSubMateri.ViewHolder>() {
    private var mData = mutableListOf<ModelSubSectionMateri?>()
    fun updateAdapter(data: ArrayList<ModelSubSectionMateri?>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSubMateri.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_sub_materi, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterSubMateri.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvTitle.text = data?.title
            when (data?.type) {
                "video" -> {
                    imgFile.setBackgroundResource(R.drawable.ic_streaming2)
                    tvDesc.text = data.materi?.desc
                }
                "pdf" -> {
                    imgFile.setBackgroundResource(R.drawable.ic_file_pdf)
                    tvDesc.text = data.materi?.description
                }
                "html" -> {
                    imgFile.setBackgroundResource(R.drawable.ic_file_html)
                }
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}