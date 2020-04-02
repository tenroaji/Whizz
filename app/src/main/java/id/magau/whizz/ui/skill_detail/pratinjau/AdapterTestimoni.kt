package id.magau.whizz.ui.skill_detail.pratinjau

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelTestimoni
import kotlinx.android.synthetic.main.item_list_file.view.tvPemateri
import kotlinx.android.synthetic.main.item_list_testimoni.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterTestimoni : RecyclerView.Adapter<AdapterTestimoni.ViewHolder>() {
    private var mData = mutableListOf<ModelTestimoni?>()

    fun updateAdapter(data : ArrayList<ModelTestimoni?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterTestimoni.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_testimoni,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterTestimoni.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvTitle.text = data?.desc
            tvPemateri.text = data?.nama
            tvPekerjaan.text = data?.pekerjaan
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}