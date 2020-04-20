package id.magau.whizz.ui.kelas.file

import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelFile
import id.magau.whizz.ui.kelas.materi.pdf.PdfActivity
import id.magau.whizz.utils.download
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.saveTo
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_file.view.*
import java.io.File


/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterFile : RecyclerView.Adapter<AdapterFile.ViewHolder>() {
    private var mData = mutableListOf<ModelFile?>()

    fun updateAdapter(data : ArrayList<ModelFile?>){
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterFile.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_file,parent,false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterFile.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvTitle.text = data?.title
            tvDownload.ripple().setOnClickListener {
//                val path = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
//                data?.path?.saveTo(path.toString())
                context.download(data?.path!!)
            }
            if (Uri.parse(data?.path).lastPathSegment?.contains("pdf")!!) {
                tvLihat.ripple().setOnClickListener {

                    context.startActivity(Intent(context, PdfActivity::class.java).apply {
                        putExtra(PdfActivity.KEY_TITLE, data?.title)
                        putExtra(PdfActivity.KEY_URL_PDF, data?.path)
                        putExtra(PdfActivity.KEY_TYPE_PDF, true)
                    })
                }
            }else{
                tvLihat visibility false
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}