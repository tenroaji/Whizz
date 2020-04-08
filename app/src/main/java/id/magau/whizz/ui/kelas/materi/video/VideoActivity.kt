package id.magau.whizz.ui.kelas.materi.video

import android.os.Bundle
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : BaseActivity(layout= R.layout.activity_video){
    companion object {
        const val KEY_VIDEO = "VIDEO"
        const val KEY_TITLE = "TITLE"
    }

    private val keyVideo by lazy{
        intent.getStringExtra(KEY_VIDEO)
    }

    private val mTitle by lazy{
        intent.getStringExtra(KEY_VIDEO)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tvTitleToolbar.text = mTitle
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