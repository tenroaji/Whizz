package id.magau.whizz.ui.kelas.materi.pdf

import android.os.Bundle
import android.os.FileUtils
import android.webkit.*
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.toast
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_pdf.*
import kotlinx.android.synthetic.main.item_loading.*
import kotlinx.android.synthetic.main.item_try_again.*

class PdfActivity : BaseActivity(layout= R.layout.activity_pdf){
    companion object {
        const val KEY_URL_PDF = "URL_PDF"
        const val KEY_TYPE_PDF = "TYPE"
        const val KEY_TITLE = "TITLE"
    }

    private val mUrl by lazy{
        intent.getStringExtra(KEY_URL_PDF)
    }

    private val mTitle by lazy{
        intent.getStringExtra(KEY_TITLE)?:""
    }

    private val mType by lazy{
        intent.getBooleanExtra(KEY_TYPE_PDF,false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewCobaLagi.ripple().setOnClickListener {
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$mUrl")
            showCobaLagi(false,null)
        }

        tvTitleToolbar.text = mTitle

        val webClient = object : WebViewClient(){
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                mProgresBar visibility false

            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
                showCobaLagi(true,null)
            }
        }

        webView.apply{
            webViewClient = webClient
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            if (mType){
            loadUrl("https://docs.google.com/gview?embedded=true&url=$mUrl")}
            else{
                loadDataWithBaseURL("", mUrl, "text/html", "UTF-8", "")
            }
        }



    }

    fun showCobaLagi(show : Boolean, data : String?){
        viewCobaLagi visibility show
        data?.let{
            tvCobaLagi.text = it
        }
    }
}