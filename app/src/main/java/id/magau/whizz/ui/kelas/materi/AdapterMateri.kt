package id.magau.whizz.ui.kelas.materi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelMateri
import id.magau.whizz.data.model.SectionModel
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_materi.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterMateri : RecyclerView.Adapter<AdapterMateri.ViewHolder>() {
    private var mData = mutableListOf<SectionModel?>()
    private var mAdapter = AdapterSubMateri()
    private var isCollapse = false
    private var myClass = false

    fun updateAdapter(data : List<SectionModel?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun updateClass(myClass : Boolean){
        this.myClass = myClass
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMateri.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_materi,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterMateri.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvTitle.text = "${position+1}. ${data?.sectionTitle}"
            mRecyclerMateri.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
            if (!data?.subSections.isNullOrEmpty()) {
                mAdapter.updateClass(myClass)
                mAdapter.updateAdapter(data?.subSections!!)
                setOnClickListener {
                    if (isCollapse) {
                        imgCollapse.rotation = 0F
                        mRecyclerMateri visibility !isCollapse
                    } else {
                        imgCollapse.rotation = 180F
                        mRecyclerMateri visibility !isCollapse
                    }
                    isCollapse = !isCollapse
                }
            } else {
                imgCollapse visibility false
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}