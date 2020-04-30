package id.magau.whizz.ui.kelas.materi.youtube

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.Window
import android.widget.SeekBar
import androidx.transition.Fade
import androidx.transition.TransitionManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import id.magau.whizz.R
import id.magau.whizz.utils.ripple
import id.magau.whizz.utils.visibility
import kotlinx.android.synthetic.main.activity_youtube.*

class YoutubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {


    companion object {
        const val KEY_VIDEO = "keyVideo"
    }

    private lateinit var apiKey: String
    private var mPlayer: YouTubePlayer? = null
    private var mHandler = Handler()
    private var mFullScreen = false
    private val keyVideo  by lazy {
        val uri= Uri.parse(intent.getStringExtra(KEY_VIDEO)?:"sGX4apYyVAo")
        uri.getQueryParameter("v")
    }
    private var mPlay = true

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_youtube)
        mButtonPlayPause.setImageResource(R.drawable.ic_pause)
        apiKey = resources.getString(R.string.youtube_key)

        mYouTubePlayerView.initialize(apiKey, this)

        val mVideoSeekBarChangeListener = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val lengthPlayed = mPlayer!!.durationMillis * progress / 100
                mPlayer!!.seekToMillis(lengthPlayed)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }



        mVideoSeekbar.setOnSeekBarChangeListener(mVideoSeekBarChangeListener)
        mVideoSeekbar.progressDrawable.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)
        mVideoSeekbar.thumb.setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN)



        mButtonPlayPause.ripple().setOnClickListener {
            if (mPlay) {
                mButtonPlayPause.setImageResource(R.drawable.ic_play)
                mPlayer?.pause()
            }else{
                mButtonPlayPause.setImageResource(R.drawable.ic_pause)
                mPlayer?.play()
            }
            mPlay = !mPlay

        }

        btnFullScreen.ripple().setOnClickListener {
            if (mFullScreen){
                btnFullScreen.setImageResource(R.drawable.ic_fullscreen_enter)
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }else{
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                btnFullScreen.setImageResource(R.drawable.ic_fullscreen_exit)
            }
            mFullScreen = !mFullScreen
        }
    }

    private var mPlaybackEventListener: YouTubePlayer.PlaybackEventListener = object :
        YouTubePlayer.PlaybackEventListener {
        override fun onBuffering(arg0: Boolean) {}

        override fun onPaused() {
            mHandler.removeCallbacks(runnable)
        }

        override fun onPlaying() {
            mHandler.postDelayed(runnable, 100)
            displayCurrentTime()
        }

        override fun onSeekTo(arg0: Int) {
            mHandler.postDelayed(runnable, 100)
        }

        override fun onStopped() {
            mHandler.removeCallbacks(runnable)
        }
    }

    private val mPlayerStateChangeListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onError(p0: YouTubePlayer.ErrorReason?) {}

        override fun onAdStarted() {}

        override fun onLoaded(arg0: String) {}

        override fun onLoading() {}

        override fun onVideoEnded() {}

        override fun onVideoStarted() {
            displayCurrentTime()
        }
    }


    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.e("lapar","$p1")
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider,
        youTubePlayer: YouTubePlayer,
        buffer: Boolean
    ) {
        this.mPlayer = youTubePlayer

        displayCurrentTime()

//             Start buffering
        if (!buffer) {
//                youTubePlayer.cueVideo(keyVideo)
            youTubePlayer.loadVideo(keyVideo)
        }
//            youTubePlayer.loadVideo(keyVideo)
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS)

        // Add listeners to YouTubePlayer instance
        youTubePlayer.setPlayerStateChangeListener(mPlayerStateChangeListener)
        youTubePlayer.setPlaybackEventListener(mPlaybackEventListener)
    }
    private var actionDownReceived = false
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                actionDownReceived = true
            }

            MotionEvent.ACTION_MOVE -> {
                // No longer a click, probably a gesture.
                actionDownReceived = false
            }

            MotionEvent.ACTION_UP -> {
                if (actionDownReceived) {
                    performClick()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun performClick(){
        val transition = Fade()
        transition.duration = 600
        transition.addTarget(viewGroup)
        TransitionManager.beginDelayedTransition(viewRoot, transition)
        Handler().postDelayed({
            viewGroup visibility !viewGroup.isShown
            if(!mPlayer?.isPlaying!! && mPlay){
                mPlayer?.play()
            }
        },600)
    }


    private fun displayCurrentTime() {
//        val formattedTime = formatTime(mPlayer.durationMillis - mPlayer.currentTimeMillis)
        val formattedTime = mPlayer?.currentTimeMillis?.let { formatTime(it) }
        val formattedTimeEnd = mPlayer?.durationMillis?.let { formatTime(it) }
        tvEndTime.text = "$formattedTime / $formattedTimeEnd"
    }

    private fun formatTime(millis: Int): String {
        val seconds = millis / 1000
        val minutes = seconds / 60
        val hours = minutes / 60

        return (if (hours == 0) "" else "$hours:") + String.format(
            "%02d:%02d",
            minutes % 60,
            seconds % 60
        )
    }


    private val runnable = object : Runnable {
        override fun run() {
            displayCurrentTime()
            mHandler.postDelayed(this, 100)
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    override fun onResume() {
        super.onResume()
        hideSystemUI()
    }

    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}