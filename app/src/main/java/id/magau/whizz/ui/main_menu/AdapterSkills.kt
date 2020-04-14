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
import kotlinx.android.synthetic.main.activity_menu.view.*
import kotlinx.android.synthetic.main.activity_skill_detail.*
import kotlinx.android.synthetic.main.item_list_skill_populer.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterSkills : RecyclerView.Adapter<AdapterSkills.ViewHolder>() {

    private var mData : MutableList<ModelProducts?> = mutableListOf()

    fun updateAdapter(data : ArrayList<ModelProducts?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_skill_populer,parent,false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = mData[position]
        holder.itemView.apply {
            if (data?.is_mine!!){
                groupPrice visibility false
            }
            data.image?.let {
                Picasso.get().load(it).into(mPlayerView)
            }
            tvJenisSkill.text = data.category?.category?.toUpperCase()
            tvTitleSkill.text = data.title
            tvHarga.text = rupiah(data.price!!)
            tvRating.text = data.rate.toString()
            if (data.promo?.is_approve == true){
                var hasil = 0
                if(data.promo?.is_percent == true){
                    val percent = data.promo?.value!! / 100
                    val harga = data.price!!
                    hasil = harga * percent
                    tvHarga.text = rupiah(hasil)
                }else{
                    hasil = data.price!! - data.promo?.value!!
                }
                tvHarga.text = rupiah(hasil)
                tvPromo promo data.price!!
            }else {
                tvHarga.text = rupiah(data.price!!)
                tvPromo visibility false
            }
            setOnClickListener {
                if(data.is_mine!!){
                    context.startActivity(Intent(context, KelasActivity::class.java).apply {
                        putExtra(KelasActivity.KEY_ID_PRODUCT,mData[position]?.uuid_course)
                        putExtra(KelasActivity.KEY_KELAS_SAYA,true)
                        putExtra(KelasActivity.KEY_PEMATERI,data.teacher?.name)
                    })
                }else{
                    context.startActivity(Intent(context, SkillDetailActivity::class.java).apply {
                        putExtra(KEY_ID_PRODUCT,mData[position]?.uuid_course)
                    })
                    (context as AppCompatActivity).overridePendingTransition(R.anim.enter, R.anim.exit)
                }

            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}