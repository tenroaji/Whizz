package id.magau.whizz.ui.skill_saya

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelSkills
import kotlinx.android.synthetic.main.item_list_skill_populer.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class AdapterSkillSaya : RecyclerView.Adapter<AdapterSkillSaya.ViewHolder>() {

    private var mData : MutableList<ModelSkills?> = mutableListOf()

    fun updateAdapter(data : ArrayList<ModelSkills>){
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
//            mData[position]?.gambar?.let {
//                Picasso.get().load(it).into(imgSkill)
//            }
            tvJenisSkill.text = mData[position]?.jenis
            tvTitleSkill.text = mData[position]?.title
            tvRating.text = mData[position]?.rate.toString()
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}