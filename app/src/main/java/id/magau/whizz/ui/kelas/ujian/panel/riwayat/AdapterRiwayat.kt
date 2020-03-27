package id.magau.whizz.ui.kelas.ujian.panel.riwayat

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelRiwayat

class AdapterRiwayat : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var data: MutableList<ModelRiwayat?> = mutableListOf()
    private var isLoading = false
    fun updateAdapter(list: List<ModelRiwayat?>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clearList() {
        isLoading = false
        data.clear()
        notifyDataSetChanged()
    }

    fun setLoading(show: Boolean) {
        if (show && !isLoading) {
            isLoading = true
            val position = data.size
            data.add(position, null)
            notifyItemInserted(data.size)
            notifyItemRangeChanged(position, data.size)
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
                mView = inflater.inflate(R.layout.item_list_riwayat, parent, false)
                holder = MyViewHolder(mView)
            }
            else -> {
                mView = inflater.inflate(R.layout.item_list_riwayat, parent, false)
                holder = MyViewHolder(mView)
            }
        }
        return holder
    }

    override fun getItemViewType(position: Int): Int {
        return if (data[position] == null && isLoading) {
            TYPE_LOADING
        } else {
            TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val mData = data[position]
        val type = getItemViewType(position)
        when (type) {
            TYPE_ITEM -> {
                (holder as MyViewHolder).itemView.apply {
//                    tvTitle.text = TimeHandler.formatDate(
//                        mData?.end_tryout!!,
//                        "yyyy-MM-dd HH:mm:ss", "dd MMM yyyy"
//                    )
//                    tvJam.text = TimeHandler.formatDate(
//                        mData.end_tryout!!,
//                        context.getString(R.string.format_before), "HH : mm "
//                    ) + "WITA"
//                    if (mData?.status.equals("lulus", true)) {
//                        tvKetLulus.text = "Lulus"
//                        imgLulus.setImageResource(R.drawable.ic_keteranganlulus)
//                    } else {
//                        tvKetLulus.text = "Tidak Lulus"
//                        imgLulus.setImageResource(R.drawable.ic_keterangantdklulus)
//                    }

                    setOnClickListener {
//                        context.startActivity(Intent(context, ActivityAnalisis::class.java).apply{
//                            putExtra(KEY_UUID,mData.uuid)
//                        })
                    }
                }

            }
        }


    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class ItemLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    companion object {
        private const val TYPE_LOADING = 1
        private const val TYPE_ITEM = 2
    }

}
