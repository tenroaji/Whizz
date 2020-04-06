package id.magau.whizz.ui.skill_saya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.data.model.ModelSkills
import id.magau.whizz.utils.radius
import kotlinx.android.synthetic.main.activity_skill_detail.view.*
import kotlinx.android.synthetic.main.item_list_skill_populer.view.*
import kotlinx.android.synthetic.main.item_list_skill_populer.view.tvRating

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterSkillSaya : RecyclerView.Adapter<AdapterSkillSaya.ViewHolder>() {

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
            imgSkill radius mData[position]?.image
            tvJenisSkill.text = mData[position]?.category?.category
            tvTitleSkill.text = mData[position]?.title
            tvRating.text = mData[position]?.rate.toString()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}