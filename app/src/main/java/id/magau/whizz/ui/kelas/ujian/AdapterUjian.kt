package id.magau.whizz.ui.kelas.ujian

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelUjian
import id.magau.whizz.ui.kelas.ujian.panel.PanelUjianActivity
import id.magau.whizz.utils.start
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_ujian.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterUjian : RecyclerView.Adapter<AdapterUjian.ViewHolder>() {
    private var mData = mutableListOf<ModelUjian?>()

    fun updateAdapter(data : ArrayList<ModelUjian?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_ujian,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            if (position == 0) {
                viewLine visibility false
                setOnClickListener {
                    context.start(PanelUjianActivity::class.java)
                }
            }
            val data = mData[position]
            tvUjian.text = data?.title
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}