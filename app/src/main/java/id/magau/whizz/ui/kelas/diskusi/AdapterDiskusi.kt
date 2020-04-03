package id.magau.whizz.ui.kelas.diskusi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelComments
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_diskusi.view.*
import kotlinx.android.synthetic.main.item_list_file.view.*
import kotlinx.android.synthetic.main.item_list_file.view.tvPemateri

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterDiskusi : RecyclerView.Adapter<AdapterDiskusi.ViewHolder>() {
    private var mData = mutableListOf<ModelComments?>()

    fun updateAdapter(data : ArrayList<ModelComments?>){
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
            tvPemateri.text = data?.user?.name?.capitalize()
            tvComment.text = data?.comment
            data?.reply?.size?.let{
                tvBalasan.text = "$it Balasan"
                if(it == 0) groupReplys visibility false
            }?: groupReplys visibility false
            viewBalasan.ripple().setOnClickListener {

            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}