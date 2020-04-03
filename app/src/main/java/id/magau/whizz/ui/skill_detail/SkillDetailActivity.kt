package id.magau.whizz.ui.skill_detail

import android.content.Intent
import android.os.Bundle
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import id.magau.whizz.R
import id.magau.whizz.data.model.ModelProducts
import id.magau.whizz.ui.kelas.KelasActivity
import id.magau.whizz.ui.pembayaran.PembayaranActivity
import id.magau.whizz.utils.*
import kotlinx.android.synthetic.main.activity_event_detail.imgBack
import kotlinx.android.synthetic.main.activity_event_detail.mTabLayout
import kotlinx.android.synthetic.main.activity_event_detail.mViewPager
import kotlinx.android.synthetic.main.activity_event_detail.tvPromo
import kotlinx.android.synthetic.main.activity_event_detail.viewButton
import kotlinx.android.synthetic.main.activity_event_detail.viewPrice
import kotlinx.android.synthetic.main.activity_skill_detail.*


/**
 * Created by Andi Tenroaji Ahmad on 3/11/2020.
 */

class SkillDetailActivity :BaseActivity(null,R.layout.activity_skill_detail),SkillDetailContracts.View{
    companion object{
        const val KEY_ID_PRODUCT = "ID_PRODUCT"
        const val KEY_PROMO = "PROMO"
        const val KEY_KELAS_SAYA = "KELAS_SAYA"
    }

    var urlImg :String? = null

    private val idProduct by lazy {
        intent.getStringExtra(KEY_ID_PRODUCT)
    }

    private val kelasSaya by lazy {
        intent.getBooleanExtra(KEY_KELAS_SAYA,false)
    }

    private lateinit var mPresenter : SkillDetailContracts.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SkillDetailPresenter(this,this)
        mPresenter.loadData(idProduct)

        val product = intent.getBooleanExtra(KEY_PROMO,false)
        if (product){
            viewButton visibility false
            val layoutParams = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            )
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            mViewPager.layoutParams = layoutParams
        }

        imgBack.setOnClickListener {
            onBackPressed()
        }

        btnBeliKelas.setOnClickListener {
            startActivity(Intent(this@SkillDetailActivity, PembayaranActivity::class.java).apply {
                putExtra(PembayaranActivity.KEY_ID_PRODUCT,idProduct)
            })
        }

        btnLihatKelas.setOnClickListener {
            startActivity(Intent(this@SkillDetailActivity, KelasActivity::class.java).apply {
                putExtra(KelasActivity.KEY_KELAS_SAYA,kelasSaya)
                putExtra(KelasActivity.KEY_ID_PRODUCT,idProduct)
                putExtra(KelasActivity.KEY_URL_IMG,urlImg)
                putExtra(KelasActivity.KEY_TITLE,tvComment.text)
            })
            overridePendingTransition(R.anim.enter, R.anim.exit)
        }

//        val playlistItem = PlaylistItem.Builder()
//            .file("https://cdn.jwplayer.com/manifests/{MEDIA_ID}.m3u8")
//            .build()
//
//        val playlist: MutableList<PlaylistItem> = ArrayList()
//        playlist.add(playlistItem)
//        val config = PlayerConfig.Builder()
//            .playlist(playlist)
//            .build()
//        mPlayerView.setup(config)

//        // Load a media source
//        val pi = PlaylistItem.Builder()
//            .file("https://playertest.longtailvideo.com/adaptive/bipbop/gear4/prog_index.m3u8")
//            .title("BipBop")
//            .description("A video player testing video.")
//            .build()
//
//        mPlayerView.load(pi)


    }


    override fun showSkill(data: ModelProducts?) {
        val mAdapter = AdapterTab(idProduct, data?.desc,supportFragmentManager)
        mViewPager.adapter = mAdapter
        mTabLayout.setupWithViewPager(mViewPager)
        urlImg = data?.image


        tvComment.text = data?.title
        tvHarga.text = rupiah(data?.price!!)
        tvJenis.text = data.category?.category
        tvPemateri.text = data.teacher?.name?.capitalize()
        imgSkill load data.image
        tvRating.text = data.rate.toString()
        if (data.promo?.is_approve == true){
            var hasil = 0
            if(data.promo?.is_percent == true){
                val percent = data.promo?.value!! / 100
                val harga = data.price!!
                hasil = harga * percent
                tvHarga.text = rupiah(hasil)

            }else{
                hasil = data.price!! - data.promo?.value!!
            }
            tvHarga.text = rupiah(hasil)
            tvPromo promo data.price!!
            val layoutParams = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    resources.getDimensionPixelSize(R.dimen.margin_64dp)
                )
            )
            layoutParams.topToBottom = tvRating.id
            layoutParams.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            layoutParams.marginStart = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParams.topMargin = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            layoutParams.marginEnd = resources.getDimensionPixelSize(R.dimen.margin_16dp)
            viewPrice.layoutParams = layoutParams
        }else {
            tvHarga.text = rupiah(data.price!!)
            tvPromo visibility false
        }

        val session = SessionUtils(this)
        val nama = session.getData(SessionUtils.PREF_KEY_NAME, "")
        val initialName = getInitialName(tvPemateri.text.toString().toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_40dp)
        val mColor = ColorGenerator.APP.getColor(nama.length)
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgPemateri.setImageDrawable(icon)
    }

    override fun showLoading(show: Boolean) {
//        mProgresBar visibility show
    }

    override fun showError(code: Int?, message: String?) {
        toast("$code -> $message")
    }

    override fun setPresenter(presenter: SkillDetailContracts.Presenter) {
        mPresenter = presenter
    }


}