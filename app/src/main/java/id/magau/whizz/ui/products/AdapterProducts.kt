package id.magau.whizz.ui.products

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.kelas.KelasActivity
import id.magau.whizz.utils.load
import kotlinx.android.synthetic.main.item_list_skill_saya.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterProducts : RecyclerView.Adapter<AdapterProducts.ViewHolder>() {

    private var mData : MutableList<ModelProducts?> = mutableListOf()

    fun updateAdapter(data : ArrayList<ModelProducts?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_skill_saya,parent,false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            imgSkill load mData[position]?.image
            tvJenisSkill.text = mData[position]?.category?.category?.toUpperCase()
            tvTitleSkill.text = mData[position]?.title
            tvRating.text = mData[position]?.rate.toString()
            setOnClickListener {
                context.startActivity(Intent(context, KelasActivity::class.java).apply {
                    putExtra(KelasActivity.KEY_ID_PRODUCT,mData[position]?.uuid_course)
                    putExtra(KelasActivity.KEY_KELAS_SAYA,true)
                })
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}