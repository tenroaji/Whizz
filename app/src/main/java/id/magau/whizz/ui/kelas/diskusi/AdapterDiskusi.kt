package id.magau.whizz.ui.kelas.diskusi

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelComments
import id.magau.whizz.ui.kelas.diskusi.balasan.BalasanActivity
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.item_list_diskusi.view.*
import kotlin.properties.Delegates

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterDiskusi : RecyclerView.Adapter<AdapterDiskusi.ViewHolder>() {
    private var mData = mutableListOf<ModelComments?>()
    private var mKelasSaya by Delegates.notNull<Boolean>()
    fun updateAdapter(data : ArrayList<ModelComments?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun updateKelasSaya(status : Boolean){
        mKelasSaya = status
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

            tvTanggal visibility false
            tvPemateri.text = data?.user?.name?.capitalize()
            tvComment.text = data?.comment
            data?.reply?.size?.let{
                tvBalasan.text = "$it Balasan"
                if(it == 0) groupReplys visibility false
            }?: groupReplys visibility false

            val nama = tvPemateri.text.toString()
            val initialName = getInitialName(nama.toUpperCase())
            val iconSize = resources.getDimensionPixelSize(R.dimen.margin_28dp)
            val mColor = ColorGenerator.APP.getColor(nama.length)
            val icon = TextDrawable.builder(context).buildRound(initialName, mColor, iconSize, iconSize)
            imgUser.setImageDrawable(icon)

            viewBalasan.ripple().setOnClickListener {
                context.startActivity(Intent(context, BalasanActivity::class.java).apply {
                    putExtra(BalasanActivity.KEY_ID_COMMENT,data?.uuid_comment)
                    putExtra(BalasanActivity.KEY_KELAS_SAYA,mKelasSaya)
                    putExtra(BalasanActivity.KEY_NAMA,tvPemateri.text.toString())
                    putExtra(BalasanActivity.KEY_COMMENT,tvComment.text.toString())
                })
            }

            setOnClickListener {
                viewBalasan.callOnClick()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}