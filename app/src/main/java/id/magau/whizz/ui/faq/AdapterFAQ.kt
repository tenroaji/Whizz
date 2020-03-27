package id.magau.whizz.ui.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFAQ
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_faq.view.*
import kotlinx.android.synthetic.main.item_list_mentor.view.*
import kotlinx.android.synthetic.main.item_list_notif.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/18/2020.
 */

class AdapterFAQ : RecyclerView.Adapter<AdapterFAQ.ViewHolder>() {

    private var mData = mutableListOf<ModelFAQ?>()

    fun updateAdapter(data: ArrayList<ModelFAQ>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFAQ.ViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_faq, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    private var mCollape = true
    override fun onBindViewHolder(holder: AdapterFAQ.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data =mData[position]
            tvTitle.text = data?.title
            tvDesc.text = data?.desc
            imgDropDown.setOnClickListener {
                if(mCollape){
                    imgDropDown.rotation = 0f
                    viewGroup visibility false
                }else{
                    imgDropDown.rotation = 90f
                    viewGroup visibility true
                }

            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}