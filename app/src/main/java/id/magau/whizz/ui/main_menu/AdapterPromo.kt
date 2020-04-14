package id.magau.whizz.ui.main_menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelEvent
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.data.model.ModelPromo
import id.magau.whizz.utils.load
import id.magau.whizz.utils.rupiah
import kotlinx.android.synthetic.main.item_list_event.view.*
import kotlinx.android.synthetic.main.item_list_event.view.tvHarga
import kotlinx.android.synthetic.main.item_list_event.view.tvJenisSkill
import kotlinx.android.synthetic.main.item_list_event.view.tvTitleSkill
import kotlinx.android.synthetic.main.item_list_promo.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterPromo : RecyclerView.Adapter<AdapterPromo.ViewHolder>() {

    private var mData : MutableList<ModelProducts?> = mutableListOf()
    private var mWidth = 0
    private var mHeight = 0

    fun updateAdapter(data : ArrayList<ModelProducts?>, width: Int, height: Int){
        mWidth = width
        mHeight = height
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_promo,parent,false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
//            val layoutParam = RecyclerView.LayoutParams(mWidth, mHeight)
            val layoutParam = RecyclerView.LayoutParams(mWidth, RecyclerView.LayoutParams.WRAP_CONTENT)

            if(position == 0) layoutParam.marginStart = context.resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParam.marginEnd = context.resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParam.bottomMargin = context.resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParams = layoutParam
//            mData[position]?.image?.let {
//                Picasso.get().load(it).into(imgEvent)
//            }
            imgPromo load mData[position]?.image
            tvRating.text = mData[position]?.rate.toString()
            tvJenisSkill.text = mData[position]?.category?.category
            tvTitleSkill.text = mData[position]?.title
            tvHarga.text = rupiah(mData[position]?.price!!)
//            tvLokasi.text = mData[position]?.lokasi
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}