package id.magau.whizz.ui.notif

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelNotif
import kotlinx.android.synthetic.main.item_list_event.view.*
import kotlinx.android.synthetic.main.item_list_notif.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterNotif : RecyclerView.Adapter<AdapterNotif.ViewHolder>() {

    private var mData : MutableList<ModelNotif?> = mutableListOf()


    fun updateAdapter(data : ArrayList<ModelNotif>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_notif,parent,false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
//            mData[position]?.gambar?.let {
//                Picasso.get().load(it).into(imgEvent)
//            }
//            tvTitle.text = "${mData[position]?.nama} ${mData[position]?.jenis} ${mData[position]?.title}"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                tvTitle.text = Html.fromHtml("<b>${mData[position]?.nama}</b> ${mData[position]?.jenis} <b>${mData[position]?.title}</b>", Html.FROM_HTML_MODE_COMPACT)
            } else {
                tvTitle.text = Html.fromHtml("<b>${mData[position]?.nama}</b> ${mData[position]?.jenis} <b>${mData[position]?.title}</b>")
            }
            tvTime.text = mData[position]?.time
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}