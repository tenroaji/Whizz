package id.magau.whizz.ui.event_detail.mentor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelMentor
import id.magau.whizz.utils.PicassoCircleTransform
import kotlinx.android.synthetic.main.item_list_mentor.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/16/2020.
 */

class AdapterMentor : RecyclerView.Adapter<AdapterMentor.ViewHolder>(){

    private var mData = mutableListOf<ModelMentor?>()

    fun updateAdapter(data : ArrayList<ModelMentor?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMentor.ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_mentor,parent,false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterMentor.ViewHolder, position: Int) {
        val data = mData[position]
        holder.itemView.apply {
//            mData[position]?.gambar?.let { Picasso.get().load(it).transform(PicassoCircleTransform()).into(imgMentor) }
            tvNama.text = data?.nama
            tvDesc.text = data?.desc
            tvPekerjaan.text = data?.pekerjaan
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}