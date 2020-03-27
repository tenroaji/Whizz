package id.magau.whizz.ui.skill_detail.pratinjau

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_faq.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterFAQ : RecyclerView.Adapter<AdapterFAQ.ViewHolder>() {
    private var mData = mutableListOf<ModelFAQ?>()
    private var isCollapse = true
    fun updateAdapter(data : ArrayList<ModelFAQ?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFAQ.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_faq,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterFAQ.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvFaq.text = data?.title
            tvDesFaq.text = data?.desc
            setOnClickListener {
                if(isCollapse){
                    imgDropDown.rotation = 0f
                    viewGroup visibility false
                }else {
                    imgDropDown.rotation = 180f
                    viewGroup visibility true
                }
                isCollapse = !isCollapse
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}