package id.magau.whizz.ui.main_menu

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import id.magau.whizz.R
import id.magau.whizz.data.model.*
import id.magau.whizz.ui.akun.AkunActivity
import id.magau.whizz.ui.event.EventActivity
import id.magau.whizz.ui.notif.NotifActivity
import id.magau.whizz.ui.products.ProductsActivity
import id.magau.whizz.ui.skill.SkillActivity
import id.magau.whizz.utils.*
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_NAME
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.item_loading.*


/**
 * Created by Andi Tenroaji Ahmad on 3/4/2020.
 */

class MainMenuActivity : BaseActivity(R.color.colorWhite,R.layout.activity_main_menu),MainMenuContracts.View{
    private lateinit var mPresenter : MainMenuContracts.Presenter
    private val mAdapterEvent = AdapterEvent()
    private val mAdapterSkills = AdapterSkills()
    private val mAdapterPromo = AdapterPromo()
    private var mWidth = 0
    private var mHeight = 0
    private var snapHelper = LinearSnapHelper()
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val session = SessionUtils(this)
        val nama = session.getData(PREF_KEY_NAME, "")
        val initialName = getInitialName(nama.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_72dp)
        val mColor = ColorGenerator.APP.getColor(nama.length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgUser.setImageDrawable(icon)

        MainMenuPresenter(this,this)
        mPresenter.start()
        mRecyclerSkillPopular.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterSkills
        }


        mRecyclerEvent.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterEvent
        }

        mRecyclerPromo.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
            adapter = mAdapterPromo
        }

        imgNotif.setOnClickListener {
            start(NotifActivity::class.java)
            notifCount visibility false
        }

        imgNotif visibility false
        notifCount visibility false

        viewSkill.setOnClickListener {
            start(SkillActivity::class.java)
        }
        viewEvent.setOnClickListener {
            startActivity(Intent(this,EventActivity::class.java).apply {
                putExtra(EventActivity.KEY_SKILL_SAYA,false)
            })
        }
        viewKelasSaya.setOnClickListener {
            start(ProductsActivity::class.java)
        }

        imgUser.ripple().setOnClickListener {
            start(AkunActivity::class.java)
        }

        tvLabelSemuaSkillPopuler.setOnClickListener {
            viewSkill.callOnClick()
        }

        tvLabelSemuaEvent.setOnClickListener {
            viewEvent.callOnClick()
        }

        tvLabelSemuaPromo.setOnClickListener {
            start(SkillActivity::class.java)
        }

        mSwipeRefresh.setOnRefreshListener {
            mPresenter.start()
        }

    }

    private fun calculateMenuSize() {
        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val margins = resources.getDimension(R.dimen.margin_16dp).toInt() * 2
        val realWitdh = width - margins
        mWidth = realWitdh - resources.getDimension(R.dimen.margin_40dp).toInt()
        mHeight = (realWitdh / 0.95F).toInt()
    }

    override fun showCount(data: Int?) {
        tvKelas.text = data.toString()
    }

    override fun showSkillPopular(data: ArrayList<ModelProducts?>) {
        mAdapterSkills.updateAdapter(data)
    }

    override fun showEvents(data: ArrayList<ModelEvents?>) {
        mAdapterEvent.updateAdapter(data)
    }

    override fun showPromo(data: ArrayList<ModelProducts?>) {
        calculateMenuSize()
        mAdapterPromo.updateAdapter(data,mWidth,mHeight)
    }

    override fun showLoading(show: Boolean) {
        mProgresBar visibility show
        if(mSwipeRefresh.isRefreshing){
            mSwipeRefresh.isRefreshing = !show
        }
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: MainMenuContracts.Presenter) {
        mPresenter = presenter
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        toast("Tekan dua kali untuk keluar dari aplikasi")
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}