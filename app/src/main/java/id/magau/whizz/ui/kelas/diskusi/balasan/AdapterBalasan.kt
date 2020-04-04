package id.magau.whizz.ui.kelas.diskusi.balasan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelReplys
import id.magau.whizz.utils.ColorGenerator
import id.magau.whizz.utils.TextDrawable
import id.magau.whizz.utils.getInitialName
import kotlinx.android.synthetic.main.activity_balasan.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterBalasan : RecyclerView.Adapter<AdapterBalasan.ViewHolder>() {
    private var mData = mutableListOf<ModelReplys?>()

    fun updateAdapter(data : ArrayList<ModelReplys?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterBalasan.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_balasan,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterBalasan.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvUser.text = data?.user?.name?.capitalize()
            tvComment.text = data?.reply
            val nama = tvUser.text.toString()
            val initialName = getInitialName(nama.toUpperCase())
            val iconSize = resources.getDimensionPixelSize(R.dimen.margin_28dp)
            val mColor = ColorGenerator.APP.getColor(nama.length)
            val icon = TextDrawable.builder(context).buildRound(initialName, mColor, iconSize, iconSize)
            imgUser.setImageDrawable(icon)

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}