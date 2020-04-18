package id.magau.whizz.ui.main_menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.ui.event_detail.EventDetailActivity
import id.magau.whizz.utils.load
import id.magau.whizz.utils.rupiah
import id.magau.whizz.utils.start
import kotlinx.android.synthetic.main.item_list_event.view.*


/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterEvent : RecyclerView.Adapter<AdapterEvent.ViewHolder>() {

    private var mData : MutableList<ModelEvents?> = mutableListOf()


    fun updateAdapter(data : ArrayList<ModelEvents?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_event,parent,false)

        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
//            mData[position]?.gambar?.let {
//                Picasso.get().load(it).into(imgEvent)
//            }
            imgEvent load data?.image
//            tvJenisSkill.text = mData[position]?.jenis
            tvTitleSkill.text = mData[position]?.event
            tvHarga.text = rupiah(mData[position]?.harga)
            tvLokasi.text = mData[position]?.kota?.toUpperCase()
            setOnClickListener {
                context.startActivity(Intent(context,EventDetailActivity::class.java).apply {
                    putExtra(EventDetailActivity.KEY_ID_EVENT, data?.uuid_events)
                })
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}