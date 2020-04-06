package id.magau.whizz.ui.kelas.materi.pdf

import android.os.Bundle
import android.os.FileUtils
import android.webkit.WebViewClient
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_pdf.*

class PdfActivity : BaseActivity(layout= R.layout.activity_pdf){
    companion object {
        const val KEY_URL_PDF = "URL_PDF"
    }

    private val mUrl by lazy{
        intent.getStringExtra(KEY_URL_PDF)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView.apply{
            webViewClient = WebViewClient()
            settings.setSupportZoom(true)
            settings.javaScriptEnabled = true
            loadUrl("https://docs.google.com/gview?embedded=true&url=$mUrl")
        }
    }
}