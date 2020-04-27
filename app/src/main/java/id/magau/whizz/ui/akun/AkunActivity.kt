package id.magau.whizz.ui.akun

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.squareup.picasso.Picasso
import id.magau.whizz.R
import id.magau.whizz.ui.login.LoginActivity
import id.magau.whizz.ui.mentor.MentorActivity
import id.magau.whizz.ui.pengaturan.PengaturanActivity
import id.magau.whizz.utils.*
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_EMAIL
import id.magau.whizz.utils.SessionUtils.Companion.PREF_KEY_NAME
import kotlinx.android.synthetic.main.activity_akun.*
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Andi Tenroaji Ahmad on 3/9/2020.
 */

class AkunActivity : BaseActivity(R.color.colorWhite, R.layout.activity_akun) {
    private val REQUEST_IMAGE_CAPTURE = 1000
    private val PERMISSION_IMAGE_CAPTURE = 1001
    private val REQUEST_IMAGE_SELECT = 2000
    private val PERMISSION_IMAGE_SELECT = 2001
    private val PERMISSION_ALL = 3000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        groupMentor visibility false
        groupPengaturan visibility false

        viewMentor.ripple().setOnClickListener {
            start(MentorActivity::class.java)
        }

        viewPengaturan.ripple().setOnClickListener {
            start(PengaturanActivity::class.java)
        }
        val session = SessionUtils(this)
        val nama by lazy { session.getData(PREF_KEY_NAME,"") }
        val initialName = getInitialName(nama.toUpperCase())
        val iconSize = resources.getDimensionPixelSize(R.dimen.margin_72dp)
        val mColor = ColorGenerator.APP.getColor(
            nama.length
        )
        tvPemateri.text = nama
        tvEmail.text = session.getData(PREF_KEY_EMAIL,"")
        val icon = TextDrawable.builder(this).buildRound(initialName, mColor, iconSize, iconSize)
        imgUser.setImageDrawable(icon)
//        imgUser.ripple().setOnClickListener {
//            val options = arrayOf("Take Photo", "Choose from Gallery", "Cancel")
//            val builder = AlertDialog.Builder(this)
//            builder.setItems(
//                options
//            ) { dialog, item ->
//                when (options[item]) {
//                    "Take Photo" -> {
//                        openCamera()
//                    }
//                    "Choose from Gallery" -> {
//                        openGalery()
//                    }
//                    "Cancel" -> {
////                        val PERMISSIONS = arrayOf(
////                            Manifest.permission.READ_EXTERNAL_STORAGE,
////                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
////                            Manifest.permission.CAMERA
////                        )
////                        if (!hasPermissions(this, *PERMISSIONS)) {
////                            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL)
////                        }
//                        dialog.dismiss()
//                    }
//                }
//            }
//            builder.show()
//        }
        viewKeluar.ripple().setOnClickListener {
            val session = SessionUtils(this)
            session.editData(SessionUtils.PREF_KEY_LOGIN, false)
            finish()
            startActivity(Intent(this@AkunActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
        }

    }

    private fun hasPermissions(context: Context, vararg permissions: String): Boolean = permissions.all {
        ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
    }


    fun openGalery() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED
        ) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_IMAGE_SELECT
                )
//                    // Show an explanation to the user *asynchronously* -- don't block
//                    // this thread waiting for the user's response! After the user
//                    // sees the explanation, try again to request the permission.
            } else {
//                    // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    PERMISSION_IMAGE_SELECT
                )

            }
        }
        // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
        // app-defined int constant. The callback method gets the
        // result of the request.
        else {
            val pickPhoto = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(
                pickPhoto,
                REQUEST_IMAGE_SELECT
            ) //one can be replaced with any action code
        }


    }

    fun openCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                )
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    PERMISSION_IMAGE_CAPTURE
                )
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    PERMISSION_IMAGE_CAPTURE
                )
            }
        } else {
            dispatchTakePictureIntent()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_IMAGE_CAPTURE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    dispatchTakePictureIntent()
                } else {
                    toast("Membutuhkan permission camera")
                }
                return
            }
            PERMISSION_IMAGE_SELECT -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    dispatchTakePictureIntent()
                } else {
                    toast("Membutuhkan permission galery")
                }
                return
            }

            else -> {
                toast("Permission Denied")
            }

        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        toast("activity result")
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Picasso.get().load(File(currentPhotoPath)).transform(PicassoCircleTransform())
                .into(imgUser)
//            val imageBitmap = data?.extras?.get("data") as Bitmap
//            imgUser.setImageBitmap(imageBitmap)
//            setPic()
        } else if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
            val selectedImage = data!!.data
            val filePathColumn =
                arrayOf(MediaStore.Images.Media.DATA)
            if (selectedImage != null) {
                val cursor: Cursor? = contentResolver.query(
                    selectedImage,
                    filePathColumn, null, null, null
                )
                if (cursor != null) {
                    cursor.moveToFirst()
                    val columnIndex: Int = cursor.getColumnIndex(filePathColumn[0])
                    val picturePath: String = cursor.getString(columnIndex)
//                        imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath))
                    Picasso.get().load(File(picturePath)).transform(PicassoCircleTransform())
                        .into(imgUser)
                    cursor.close()
                }
            }
        }
    }


    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI = FileProvider.getUriForFile(
                        this,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    lateinit var currentPhotoPath: String

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        lateinit var imageDir: File
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
//            val baseDir = File(Environment.getExternalStorageDirectory(), "Whizz")
//            if (!baseDir.exists()){
//                baseDir.mkdir()
//            }
//            imageDir = File(baseDir, "Image")
//            if (!imageDir.exists()){
//                imageDir.mkdir()
//            }
//        } else {
        imageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
//        }

        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            imageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }


}