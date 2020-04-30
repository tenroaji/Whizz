package id.magau.whizz.ui.products

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.ui.event_detail.EventDetailActivity
import id.magau.whizz.utils.load

import kotlinx.android.synthetic.main.item_list_event.view.tvTitleSkill
import kotlinx.android.synthetic.main.item_list_events_saya.view.*


/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterEventSaya : RecyclerView.Adapter<AdapterEventSaya.ViewHolder>() {

    private var mData : MutableList<ModelEvents?> = mutableListOf()


    fun updateAdapter(data : ArrayList<ModelEvents?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_events_saya,parent,false)
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
            val data =  mData[position]
            tvTitleSkill.text = data?.event
            tvTanggal.text = data?.tanggalWeb?.replace("-"," ")
            imgEvent load data?.image
            setOnClickListener {
                context.startActivity(Intent(context, EventDetailActivity::class.java).apply {
                    putExtra(EventDetailActivity.KEY_ID_EVENT,data?.uuid_events)
                    putExtra(EventDetailActivity.KEY_MY_PRODUCT,true)
                })
                (context as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}