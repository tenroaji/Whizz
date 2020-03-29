package id.magau.whizz.ui.products

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelEventSaya
import id.magau.whizz.ui.event_detail.EventDetailActivity
import id.magau.whizz.ui.event_detail.EventDetailActivity.Companion.KEY_PRODUCT
import id.magau.whizz.utils.start
import kotlinx.android.synthetic.main.item_list_event.view.*
import kotlinx.android.synthetic.main.item_list_event.view.tvTitleSkill
import kotlinx.android.synthetic.main.item_list_events_saya.view.*


/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterEventSaya : RecyclerView.Adapter<AdapterEventSaya.ViewHolder>() {

    private var mData : MutableList<ModelEventSaya?> = mutableListOf()


    fun updateAdapter(data : ArrayList<ModelEventSaya>){
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
            tvTitleSkill.text = mData[position]?.title
            tvTanggal.text = mData[position]?.tanggal
            setOnClickListener {
                context.startActivity(Intent(context, EventDetailActivity::class.java).apply {
                    putExtra(KEY_PRODUCT,true)
                })
                (context as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}