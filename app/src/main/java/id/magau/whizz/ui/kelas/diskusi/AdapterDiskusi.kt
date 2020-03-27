package id.magau.whizz.ui.kelas.diskusi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.data.model.ModelKurikulum
import kotlinx.android.synthetic.main.item_list_file.view.*
import kotlinx.android.synthetic.main.item_list_kurikulum.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterDiskusi : RecyclerView.Adapter<AdapterDiskusi.ViewHolder>() {
    private var mData = mutableListOf<ModelFile?>()

    fun updateAdapter(data : ArrayList<ModelFile?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDiskusi.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_diskusi,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterDiskusi.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvNama.text = data?.nama
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}