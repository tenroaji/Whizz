package id.magau.whizz.ui.skill_detail.kurikulum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelSubSection
import kotlinx.android.synthetic.main.item_list_sub_section.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterSubKurikulum : RecyclerView.Adapter<AdapterSubKurikulum.ViewHolder>() {
    private var mData = mutableListOf<ModelSubSection?>()

    fun updateAdapter(data : ArrayList<ModelSubSection?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSubKurikulum.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_sub_section,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterSubKurikulum.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvNo.text = "${position+1} ."
            tvTitle.text = "${data?.title} ."
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}