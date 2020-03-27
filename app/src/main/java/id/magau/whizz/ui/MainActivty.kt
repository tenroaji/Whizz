package id.magau.whizz.ui

import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.*
import id.magau.whizz.R


/**
 * Created by Andi Tenroaji Ahmad on 2/28/2020.
 */

class MainActivty : AppCompatActivity(R.layout.activity_main) {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val toolbar = findViewById<Toolbar>(R.id.mToolbar)
//        setSupportActionBar(toolbar)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.setHomeButtonEnabled(true)
//        supportActionBar?.setDisplayShowTitleEnabled(false)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.apply {
//                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                val winParams = attributes.apply {
                    flags = flags and WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    flags = WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS
                }
                attributes = winParams
                statusBarColor = ContextCompat.getColor(context, R.color.colorWhite)
            }
        }
        navController = findNavController(R.id.nav_host_fragment)
        appBarConfiguration = AppBarConfiguration(navController.graph)


        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.daftarActivity -> {
//                    findViewById<Toolbar>(R.id.mToolbar) visibility false
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.apply {
                            statusBarColor = ContextCompat.getColor(context, R.color.colorPrimary)
                        }
                    }
                }
                R.id.mainMenu -> {
//                mToolbar visibility false
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.apply {
                            statusBarColor = ContextCompat.getColor(context, R.color.colorWhite)
                        }
                    }
                }
                R.id.menuActivity -> {
//                    mToolbar visibility true
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.apply {
                            statusBarColor = ContextCompat.getColor(context, R.color.colorWhite)
                        }
                    }
                }

                R.id.loginActivity -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.apply {
                            statusBarColor = ContextCompat.getColor(context, R.color.colorWhite)
                        }
                    }
                }
                else -> {
                    //                mToolbar visibility false
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        window.apply {
                            statusBarColor =
                                ContextCompat.getColor(context, R.color.colorPrimaryDark)
                        }
                    }
                }
            }

        }

//        onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                if(!navController.popBackStack()){
//                    finish()
//                }
//            }
//        })
    }

//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.home -> {
                navController.popBackStack()
            }

        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(
            item
        )

//        return super.onOptionsItemSelected(item)
    }

}