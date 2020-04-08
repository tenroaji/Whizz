package id.magau.whizz.ui.kelas.materi.video

import android.os.Bundle
import android.os.FileUtils
import android.webkit.WebViewClient
import com.google.android.gms.cast.framework.CastContext
import com.longtailvideo.jwplayer.configuration.PlayerConfig
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_pdf.*
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : BaseActivity(layout= R.layout.activity_video){
    companion object {
        const val KEY_VIDEO = "VIDEO"
    }

    private val keyVideo by lazy{
        intent.getStringExtra(KEY_VIDEO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val playlistItem = PlaylistItem.Builder()
            .file("https://cdn.jwplayer.com/manifests/$keyVideo.m3u8")
            .build()

//        val playlist: MutableList<PlaylistItem> = ArrayList()
//        playlist.add(playlistItem)
//        val config = PlayerConfig.Builder()
//            .playlist(playlist)
//            .build()
//        mPlayerView.setup(config)
        mPlayerView.load(playlistItem)
//        val mCastContext = CastContext.getSharedInstance(this);
    }

    override fun onStart() {
        super.onStart()
        mPlayerView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mPlayerView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mPlayerView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mPlayerView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayerView.onDestroy()
    }
}