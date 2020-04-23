package id.magau.whizz.ui.main_menu

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.kelas.KelasActivity
import id.magau.whizz.ui.skill_detail.SkillDetailActivity
import id.magau.whizz.ui.skill_detail.SkillDetailActivity.Companion.KEY_ID_PRODUCT
import id.magau.whizz.utils.promo
import id.magau.whizz.utils.rupiah
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_skill_populer.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterSkills : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var mData: MutableList<ModelProducts?> = mutableListOf()
    private var isLoading = false

    fun updateAdapter(data: ArrayList<ModelProducts?>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_skill_populer,parent,false)
//        return ViewHolder(mView)

        val mView: View
        val holder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)

        when (viewType) {
            TYPE_LOADING -> {
                mView = inflater.inflate(R.layout.item_view_loader, parent, false)
                holder = ItemLoadingViewHolder(mView)
            }
            TYPE_ITEM -> {
                mView = inflater.inflate(R.layout.item_list_skill_populer, parent, false)
                holder = ViewHolder(mView)
            }
            else -> {
                mView = inflater.inflate(R.layout.item_list_skill_populer, parent, false)
                holder = ViewHolder(mView)
            }
        }
        return holder

    }

    override fun getItemCount(): Int {
        return mData.size
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
                    if (data?.is_mine!!) {
                        groupPrice visibility false
                    }
                    data.image?.let {
                        Picasso.get().load(it).into(mPlayerView)
                    }
                    tvJenisSkill.text = data.category?.category?.toUpperCase()
                    tvTitleSkill.text = data.title
                    tvHarga.text = rupiah(data.price!!)
                    tvRating.text = data.rate.toString()
                    if (data.promo?.is_approve == true) {
//                var hasil = 0
//                if(data.promo?.is_percent == true){
//                    val percent = data.promo?.value!! / 100
//                    val harga = data.price!!
//                    hasil = harga * percent
//                    tvHarga.text = rupiah(hasil)
//                }else{
//                    hasil = data.price!! - data.promo?.value!!
//                }
                        tvHarga.text = rupiah(data.promo?.value!!)
                        tvPromo promo data.price!!
                    } else {
                        tvHarga.text = rupiah(data.price!!)
                        tvPromo visibility false
                    }
                    setOnClickListener {
                        if (data.is_mine!!) {
                            context.startActivity(Intent(context, KelasActivity::class.java).apply {
                                putExtra(KelasActivity.KEY_ID_PRODUCT, mData[position]?.uuid_course)
                                putExtra(KelasActivity.KEY_KELAS_SAYA, true)
                                putExtra(KelasActivity.KEY_PEMATERI, data.teacher?.name)
                            })
                        } else {
                            context.startActivity(
                                Intent(
                                    context,
                                    SkillDetailActivity::class.java
                                ).apply {
                                    putExtra(KEY_ID_PRODUCT, mData[position]?.uuid_course)
                                })
                            (context as AppCompatActivity).overridePendingTransition(
                                R.anim.enter,
                                R.anim.exit
                            )
                        }

                    }
                }
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class ItemLoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    companion object {
        private const val TYPE_LOADING = 1
        private const val TYPE_ITEM = 2
    }
}