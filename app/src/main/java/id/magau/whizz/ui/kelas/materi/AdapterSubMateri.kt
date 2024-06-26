package id.magau.whizz.ui.kelas.materi

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.magau.whizz.R
import id.magau.whizz.data.model.*
import id.magau.whizz.ui.kelas.materi.pdf.PdfActivity
import id.magau.whizz.ui.kelas.materi.soal.SoalActivity
import id.magau.whizz.ui.kelas.materi.video.VideoActivity
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.item_list_sub_materi.view.*

/**
 * Created by Andi Tenroaji Ahmad on 3/19/2020.
 */

class AdapterSubMateri : RecyclerView.Adapter<AdapterSubMateri.ViewHolder>() {
    private var mData = mutableListOf<CourseTypeModel?>()
    private var myClass = false
    fun updateAdapter(data: List<CourseTypeModel?>) {
        mData.clear()
        mData.addAll(data)
        notifyDataSetChanged()
    }


    fun updateClass(myClass : Boolean){
        this.myClass = myClass
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
            when (data) {
                is CourseVideoModel -> {
                    val materi = data.course
                    imgFile.setBackgroundResource(R.drawable.ic_streaming2)
                    tvDesc visibility false
                    if (myClass) {
                        tvDesc visibility true
                        tvDesc.text = materi.description
                        setOnClickListener {
                            context.startActivity(Intent(context, VideoActivity::class.java).apply {
                                putExtra(VideoActivity.KEY_VIDEO, materi.videos[0].link)
                                putExtra(VideoActivity.KEY_TITLE, materi.title)
                            })
                        }
                    }
                    tvTitle.text = data.title
                }
                is CoursePdfModel  -> {
                    val materi = data.course
                    imgFile.setBackgroundResource(R.drawable.ic_file_pdf)
                    tvDesc visibility false
                    if (myClass) {
                        tvDesc visibility true
                        tvDesc.text = materi.description
                        setOnClickListener {
                        context.startActivity(Intent(context, PdfActivity::class.java).apply {
                            putExtra(PdfActivity.KEY_URL_PDF, materi.fileUrl)
                            putExtra(PdfActivity.KEY_TYPE_PDF, true)
                            putExtra(PdfActivity.KEY_TITLE, materi.title)
                        })
                    }
                    }
                    tvTitle.text = data.title
                }
                is CourseHtmlModel -> {
                    val materi = data.course
                    imgFile.setBackgroundResource(R.drawable.ic_file_html)
                    tvDesc visibility false
                    setOnClickListener {
                        context.startActivity(Intent(context, PdfActivity::class.java).apply {
                            putExtra(PdfActivity.KEY_URL_PDF, materi.rawHtml)
                            putExtra(PdfActivity.KEY_TYPE_PDF, false)
                            putExtra(PdfActivity.KEY_TITLE, materi.title)
                        })
                    }
                    tvTitle.text = data.title
                }
                is CourseExamModel -> {
                    imgFile.setBackgroundResource(R.drawable.ic_file_ujian)
                    val materi = data.course
                    if (myClass) {
                        setOnClickListener {
                            context.startActivity(Intent(context, SoalActivity::class.java).apply {
                            materi?.let{
                                putExtra(SoalActivity.KEY_DATA_SOAL, it)
                                putExtra(SoalActivity.KEY_UUID, data.uuid_sub_section)
                            }
//                            putExtra(PdfActivity.KEY_TYPE_PDF, false)
//                            putExtra(PdfActivity.KEY_TITLE, data.materi?.title)
                            })
                        }
                    }
                    tvTitle.text = data.title
                    tvDesc visibility false
                }
                else -> {
                    tvDesc visibility false
                }
            }

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}