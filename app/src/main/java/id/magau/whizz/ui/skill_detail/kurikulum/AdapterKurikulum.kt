package id.magau.whizz.ui.skill_detail.kurikulum

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelKurikulum
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_kurikulum.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterKurikulum : RecyclerView.Adapter<AdapterKurikulum.ViewHolder>() {
    private var mData = mutableListOf<ModelKurikulum?>()
    private var mAdapter = AdapterSubKurikulum()
    private var isCollapse = true
    fun updateAdapter(data : ArrayList<ModelKurikulum?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterKurikulum.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_kurikulum,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterKurikulum.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
//            tvNo.text = "${position+1} ."
            tvTitle.text = "${data?.title} ."
            mRecyclerSubSection.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            if (!data?.subSection.isNullOrEmpty()){
                mAdapter.updateAdapter(data?.subSection!!)

                setOnClickListener {
                    if (isCollapse){
                        imgCollapse.rotation = 0F
                        group visibility !isCollapse
                    }else{
                        imgCollapse.rotation = 180F
                        group visibility !isCollapse
                    }
                    isCollapse = !isCollapse
                }
            }else{
                imgCollapse visibility false
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}