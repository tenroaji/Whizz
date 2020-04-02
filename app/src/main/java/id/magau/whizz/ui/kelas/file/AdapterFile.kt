package id.magau.whizz.ui.kelas.file

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFile
import kotlinx.android.synthetic.main.item_list_file.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterFile : RecyclerView.Adapter<AdapterFile.ViewHolder>() {
    private var mData = mutableListOf<ModelFile?>()

    fun updateAdapter(data : ArrayList<ModelFile?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFile.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_file,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterFile.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvPemateri.text = data?.nama

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}