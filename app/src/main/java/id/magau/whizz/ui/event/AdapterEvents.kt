package id.magau.whizz.ui.event

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvents
import id.magau.whizz.ui.event_detail.EventDetailActivity
import id.magau.whizz.utils.load
import id.magau.whizz.utils.rupiah
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_event.view.imgEvent
import kotlinx.android.synthetic.main.item_list_event.view.tvHarga
import kotlinx.android.synthetic.main.item_list_event.view.tvLokasi
import kotlinx.android.synthetic.main.item_list_event.view.tvTitleSkill
import kotlinx.android.synthetic.main.item_list_events.view.*


/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterEvents : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mData: MutableList<ModelEvents?> = mutableListOf()
    private var isLoading = false
    private var myClass = false

    fun updateAdapter(data: ArrayList<ModelEvents?>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun updateClass(myClass : Boolean){
        this.myClass = myClass
    }

    fun clearList() {
        isLoading = false
        mData.clear()
        notifyDataSetChanged()
    }

    fun setLoading(show: Boolean) {
        if (show && !isLoading) {
            isLoading = true
            val position = mData.size
            mData.add(position, null)
            notifyItemInserted(mData.size)
            notifyItemRangeChanged(position, mData.size)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val mView: View
        val holder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TYPE_LOADING -> {
                mView = inflater.inflate(R.layout.item_view_loader, parent, false)
                holder = ItemLoadingViewHolder(mView)
            }
            TYPE_ITEM -> {
                mView = inflater.inflate(R.layout.item_list_events, parent, false)
                holder = MyViewHolder(mView)
            }
            else -> {
                mView = inflater.inflate(R.layout.item_list_events, parent, false)
                holder = MyViewHolder(mView)
            }
        }
        return holder

//        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_event,parent,false)
//
//        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (mData[position] == null && isLoading) {
            TYPE_LOADING
        } else {
            TYPE_ITEM
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val data = mData[position]
        val type = getItemViewType(position)
        when (type) {
            TYPE_ITEM -> {
                holder.itemView.apply {
//            mData[position]?.gambar?.let {
//                Picasso.get().load(it).into(imgEvent)
//            }
                    imgEvent load data?.image
//            tvJenisSkill.text = data?.jenis
                    tvTitleSkill.text = data?.event


                    if (myClass){
                        groupTanggal visibility true
                        groupPrice visibility false
                        tvTanggal.text = data?.tanggalWeb?.replace("-"," ")
                    }else{
                        tvLokasi.text = data?.kota?.toUpperCase()
                        tvHarga.text = rupiah(data?.harga!!)
                    }
                    setOnClickListener {
//                        context.start(EventDetailActivity::class.java)
                        context.startActivity(
                            Intent(
                                context,
                                EventDetailActivity::class.java
                            ).apply {
                                putExtra(EventDetailActivity.KEY_ID_EVENT, data?.uuid_events)
                                putExtra(EventDetailActivity.KEY_MY_PRODUCT, myClass)
                            })
                    }
                }
            }
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ItemLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    companion object {
        private const val TYPE_LOADING = 1
        private const val TYPE_ITEM = 2
    }

}