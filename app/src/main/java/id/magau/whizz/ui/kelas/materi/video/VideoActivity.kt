package id.magau.whizz.ui.kelas.materi.video

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.github.mervick.aes_everywhere.legacy.Aes256
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import id.magau.whizz.R
import id.magau.whizz.utils.BaseActivity
import id.magau.whizz.utils.passPhrase
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
        intent.getStringExtra(KEY_TITLE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvTitleToolbar.text = mTitle

        var mUrl = Aes256.decrypt(keyVideo, passPhrase("cc07e936baf2ff346911af4bb44c2867"))
        Log.e("lapar aes",mUrl)
        val uri = Uri.parse(mUrl)
        val server = uri.authority
        val path = uri.path
        val protocol = uri.scheme
        val args = uri.queryParameterNames
        val params = uri.getQueryParameter("driveid")
        Log.d("lapar", "server : $server")
        Log.d("lapar", "path : $path")
        Log.d("lapar", "protocol : $protocol")
        Log.d("lapar", "args : $args")
        Log.d("lapar", "params : $params")
        mUrl = "https://www.youtube.com/watch?v=2UzucB6YMkw"
        val bandwidthMeter: BandwidthMeter = DefaultBandwidthMeter()
        val videoTrackSelectionFactory: TrackSelection.Factory =
            AdaptiveTrackSelection.Factory(bandwidthMeter)
        val trackSelector: TrackSelector = DefaultTrackSelector(videoTrackSelectionFactory)
        val exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        val audioSource: MediaSource = ExtractorMediaSource(
            Uri.parse(mUrl),
            CacheDataSourceFactory(
                this,
                100 * 1024 * 1024,
                5 * 1024 * 1024
            ), DefaultExtractorsFactory(), null, null
        )
        exoPlayer.playWhenReady = true
        exoPlayer.prepare(audioSource)
        mPlayerView.player = exoPlayer
    }

}