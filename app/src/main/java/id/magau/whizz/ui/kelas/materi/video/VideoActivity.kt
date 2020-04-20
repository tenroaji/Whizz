package id.magau.whizz.ui.kelas.materi.video

import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.github.mervick.aes_everywhere.legacy.Aes256
import com.google.android.exoplayer2.BuildConfig
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.mp4.Mp4Extractor
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.upstream.BandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
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

    private lateinit var exoPlayer : SimpleExoPlayer
    private lateinit var dataSourceFactory : DefaultDataSourceFactory
    private lateinit var userAgent : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        tvTitleToolbar.text = mTitle

        val mUrl = Aes256.decrypt(keyVideo, passPhrase("cc07e936baf2ff346911af4bb44c2867"))
//        Log.e("lapar aes",mUrl)
//        val uri = Uri.parse(mUrl)
//        val server = uri.authority
//        val path = uri.path
//        val protocol = uri.scheme
//        val args = uri.queryParameterNames
//        val params = uri.getQueryParameter("driveid")
//        Log.d("lapar", "server : $server")
//        Log.d("lapar", "path : $path")
//        Log.d("lapar", "protocol : $protocol")
//        Log.d("lapar", "args : $args")
//        Log.d("lapar", "params : $params")

        exoPlayer = SimpleExoPlayer.Builder(this).build()
        userAgent = Util.getUserAgent(this, BuildConfig.APPLICATION_ID)
         dataSourceFactory = DefaultDataSourceFactory(this,
             userAgent)

        val videoSource = buildMediaSource(Uri.parse(mUrl))
        exoPlayer.playWhenReady = true
        exoPlayer.prepare(videoSource)
        mPlayerView.player = exoPlayer

    }

    private fun buildMediaSource(uri: Uri): MediaSource {


        if (uri.lastPathSegment!!.contains("mp3") || uri.lastPathSegment!!.contains("mp4")) {
            val extractorsFactory=  DefaultExtractorsFactory()
                .setMp4ExtractorFlags(Mp4Extractor.FLAG_WORKAROUND_IGNORE_EDIT_LISTS)
            return ProgressiveMediaSource.Factory(dataSourceFactory, extractorsFactory)
                .createMediaSource(uri)
        } else if (uri.lastPathSegment!!.contains("m3u8")) {
            return HlsMediaSource.Factory(DefaultHttpDataSourceFactory(BuildConfig.APPLICATION_ID))
                .createMediaSource(uri)
        }
        else {
            val dashChunkSourceFactory = DefaultDashChunkSource.Factory(
                DefaultHttpDataSourceFactory("ua", DefaultBandwidthMeter()))
            val manifestDataSourceFactory = DefaultHttpDataSourceFactory(userAgent)
            return DashMediaSource.Factory(dashChunkSourceFactory, manifestDataSourceFactory).createMediaSource(uri)
        }
//        else {
//            val dashChunkSourceFactory = DefaultDashChunkSource.Factory(
//                DefaultHttpDataSourceFactory("ua", BANDWIDTH_METER))
//            val manifestDataSourceFactory = DefaultHttpDataSourceFactory(userAgent)
//            return DashMediaSource.Factory(dashChunkSourceFactory, manifestDataSourceFactory).createMediaSource(uri)
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer.release()
    }

}