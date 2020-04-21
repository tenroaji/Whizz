package id.magau.whizz.ui.event_detail.mentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelPemateri
import id.magau.whizz.utils.ColorGenerator
import id.magau.whizz.utils.TextDrawable
import id.magau.whizz.utils.getInitialName
import id.magau.whizz.utils.load
import kotlinx.android.synthetic.main.item_list_mentor.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/16/2020.
 */

class AdapterMentor : RecyclerView.Adapter<AdapterMentor.ViewHolder>() {

    private var mData = mutableListOf<ModelPemateri?>()

    fun updateAdapter(data: List<ModelPemateri?>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMentor.ViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_mentor, parent, false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterMentor.ViewHolder, position: Int) {
        val data = mData[position]
        holder.itemView.apply {
            if (data?.gambar.isNullOrEmpty()) {
                val nama = data?.nama
                val initialName = getInitialName(nama!!.toUpperCase())
                val iconSize = resources.getDimensionPixelSize(R.dimen.margin_56dp)
                val mColor = ColorGenerator.APP.getColor(
                    nama.length
                )
                val icon = TextDrawable.builder(context)
                    .buildRound(initialName, mColor, iconSize, iconSize)
                imgMentor.setImageDrawable(icon)
            } else {
                imgMentor load data?.gambar
            }


            tvPemateri.text = data?.nama
            tvComment.text = data?.desc
            tvPekerjaan.text = data?.job_title
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}