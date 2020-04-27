package id.magau.whizz.ui.kelas.materi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelStreaming
import kotlinx.android.synthetic.main.item_list_streaming.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterStreaming : RecyclerView.Adapter<AdapterStreaming.ViewHolder>() {
    private var mData = mutableListOf<ModelStreaming?>()
    private var myClass = false

    fun updateAdapter(data : ArrayList<ModelStreaming?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun updateClass(myClass : Boolean){
        this.myClass = myClass
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterStreaming.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_streaming,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterStreaming.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
          tvTanggal.text = data?.tanggalWeb?.replace("-", " ")
            tvWaktu.text = "${data?.waktuWeb} WITA"
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}