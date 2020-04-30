package id.magau.whizz.ui.pembayaran

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelPembayaran
import id.magau.whizz.ui.pembayaran_detail.PembayaranDetail
import id.magau.whizz.ui.pembayaran_detail.PembayaranDetail.Companion.KEY_ID_PRODUCT
import id.magau.whizz.ui.pembayaran_detail.PembayaranDetail.Companion.KEY_IMAGE_BANK
import id.magau.whizz.ui.pembayaran_detail.PembayaranDetail.Companion.KEY_KODE_BANK
import id.magau.whizz.ui.pembayaran_detail.PembayaranDetail.Companion.KEY_TITLE_BANK
import id.magau.whizz.utils.start
import kotlinx.android.synthetic.main.item_list_pembayaran.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterPembayaran : RecyclerView.Adapter<AdapterPembayaran.ViewHolder> (){

    var mData = mutableListOf<ModelPembayaran?>()
    var idProduct = ""
    var mType = ""
    var mPrice = ""
    fun updateAdapter(data : ArrayList<ModelPembayaran?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    fun updateData(idProduct : String,type : String,price : String){
        this.idProduct=idProduct
        this.mType = type
        this.mPrice = price
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mView = LayoutInflater.from(parent.context).inflate(R.layout.item_list_pembayaran,parent,false)
        return ViewHolder(mView)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvNamaBank.text = data?.nama
            setOnClickListener {
//                context.start(PembayaranDetail::class.java)
                context.startActivity(Intent(context, PembayaranDetail::class.java).apply {
                    putExtra(KEY_ID_PRODUCT,idProduct)
                    putExtra(KEY_KODE_BANK,data?.kode)
                    putExtra(KEY_TITLE_BANK,data?.nama)
                    putExtra(KEY_IMAGE_BANK,data?.img)
                    putExtra(PembayaranDetail.KEY_TYPE,mType)
                    putExtra(PembayaranDetail.KEY_PRICE,mPrice)
                })
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}