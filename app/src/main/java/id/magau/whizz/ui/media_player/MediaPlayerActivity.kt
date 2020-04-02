package id.magau.whizz.ui.media_player

import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.cast.framework.CastContext
import com.longtailvideo.jwplayer.configuration.PlayerConfig
import com.longtailvideo.jwplayer.media.playlists.PlaylistItem
import id.magau.whizz.R
import kotlinx.android.synthetic.main.activity_media_player.*


/**
 * Created by Andi Tenroaji Ahmad on 3/31/2020.
 */

class MediaPlayerActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media_player)


        val playlistItem = PlaylistItem.Builder()
            .file("https://cdn.jwplayer.com/manifests/R6f1893l.m3u8")
            .build()

        val playlist: MutableList<PlaylistItem> = ArrayList()
        playlist.add(playlistItem)
        val config = PlayerConfig.Builder()
            .playlist(playlist)
            .build()
        mPlayerView.setup(config)

        val mCastContext = CastContext.getSharedInstance(this);
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


//    override fun onConfigurationChanged(newConfig: Configuration) {
//        // Set fullscreen when the device is rotated to landscape
//        mPlayerView.setFullscreen(
//            newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE,
//            true
//        )
//        super.onConfigurationChanged(newConfig)
//    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Exit fullscreen when the user pressed the Back button
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mPlayerView.fullscreen) {
                mPlayerView.setFullscreen(false, true)
                return false
            }
        }
        return super.onKeyDown(keyCode, event)
    }


}