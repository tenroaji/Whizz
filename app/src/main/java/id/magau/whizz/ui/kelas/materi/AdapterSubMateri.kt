package id.magau.whizz.ui.kelas.materi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelSubSectionMateri
import id.magau.whizz.ui.kelas.materi.pdf.PdfActivity
import id.magau.whizz.ui.kelas.materi.video.VideoActivity
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_materi.view.tvTitle
import kotlinx.android.synthetic.main.item_list_sub_materi.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterSubMateri : RecyclerView.Adapter<AdapterSubMateri.ViewHolder>() {
    private var mData = mutableListOf<ModelSubSectionMateri?>()
    fun updateAdapter(data: ArrayList<ModelSubSectionMateri?>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterSubMateri.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list_sub_materi, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: AdapterSubMateri.ViewHolder, position: Int) {
        holder.itemView.apply {
            val data = mData[position]
            tvTitle.text = data?.title
            when (data?.type) {
                "video" -> {
                    imgFile.setBackgroundResource(R.drawable.ic_streaming2)
                    tvDesc.text = data.materi?.desc
                    setOnClickListener {
                        context.startActivity(Intent(context, VideoActivity::class.java).apply {
                            putExtra(VideoActivity.KEY_VIDEO, data.materi?.video)
                            putExtra(VideoActivity.KEY_TITLE, data.materi?.title)
                        })
                    }
                }
                "pdf" -> {
                    imgFile.setBackgroundResource(R.drawable.ic_file_pdf)
                    tvDesc.text = data.materi?.description
                    setOnClickListener {
                        context.startActivity(Intent(context, PdfActivity::class.java).apply {
                            putExtra(PdfActivity.KEY_URL_PDF, data.materi?.pdf)
                            putExtra(PdfActivity.KEY_TYPE_PDF, true)
                            putExtra(PdfActivity.KEY_TITLE, data.materi?.title)
                        })
                    }
                }
                "html" -> {
                    imgFile.setBackgroundResource(R.drawable.ic_file_html)
                    tvDesc visibility false
                    setOnClickListener {
                        context.startActivity(Intent(context, PdfActivity::class.java).apply {
                            putExtra(PdfActivity.KEY_URL_PDF, data.materi?.html)
                            putExtra(PdfActivity.KEY_TYPE_PDF, false)
                            putExtra(PdfActivity.KEY_TITLE, data.materi?.title)
                        })
                    }
                }
                else -> {
                    tvDesc visibility false
                }
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}