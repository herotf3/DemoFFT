package com.example.tf.fft.kotlin

/**
 * Created by macbook on 26/2/19.
 */

import android.Manifest
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.tf.fft.R
import com.example.tf.fft.databinding.ActivityMainBinding
import com.scichart.charting.themes.ThemeManager
import com.example.tf.fft.kotlin.utils.PermissionManager
import com.example.tf.fft.kotlin.views.AudioAnalyzerFragment

// The main activity for the application
class MainActivity : AppCompatActivity() {
    private var activityBinding: ActivityMainBinding? = null

    private val permissionManager = PermissionManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpSciChartLicense()
        requestPermissions()
        initAppBar()
        setUpThemes()

//        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, HomePageFragment()).commit()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, AudioAnalyzerFragment()).commit()
        supportFragmentManager.addOnBackStackChangedListener {
            val isBackStackEmpty = supportFragmentManager.backStackEntryCount > 0
            supportActionBar!!.setDisplayHomeAsUpEnabled(isBackStackEmpty)
            activityBinding!!.logo.visibility = if (isBackStackEmpty) View.GONE else View.VISIBLE
        }
    }

    private fun setUpThemes() {
        ThemeManager.addTheme(this, R.style.SciChart_Bright_Spark)
    }

    private fun requestPermissions() {
        // permissions for Audio Analyzer
        permissionManager.requestPermission(Manifest.permission.RECORD_AUDIO)
        permissionManager.requestPermission(Manifest.permission.MODIFY_AUDIO_SETTINGS)

        // permissions for SciTrader
        permissionManager.requestPermission(Manifest.permission.INTERNET)
        permissionManager.requestPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    }

    private fun initAppBar() {
        setSupportActionBar(activityBinding!!.appToolbar)

        activityBinding!!.appToolbar.setNavigationOnClickListener({ onBackPressed() })
    }

    private fun setUpSciChartLicense() {
        try {
            com.scichart.charting.visuals.SciChartSurface.setRuntimeLicenseKey("<LicenseContract>\n" +
                    "  <Customer>thientf.97@gmail.com</Customer>\n" +
                    "  <OrderId>Trial</OrderId>\n" +
                    "  <LicenseCount>1</LicenseCount>\n" +
                    "  <IsTrialLicense>true</IsTrialLicense>\n" +
                    "  <SupportExpires>03/29/2019 00:00:00</SupportExpires>\n" +
                    "  <ProductCode>SC-ANDROID-2D-ENTERPRISE-SRC</ProductCode>\n" +
                    "  <KeyCode>b831c51a80ae06b421e0241c344f553b72e0e2fbd0208cd500160293d32649aee6fbbb0d03d72b5c4f6e01512dea3b9ae469b2f28da6471d3814275af8e5cd37e7b3520747e13b19ec29eeb1e862ac221525419763af43c6077c1d64eb0877500f9b53f7c11aba26030696147960d6e7631c4589693e2addb4614877e972209f5ad5bb55da677107c9e2d42000408b4f0cd40ffa0860ae4809053495c2220a1124e274b4151b9afd72458f443364</KeyCode>\n" +
                    "</LicenseContract>")
        } catch (e: Exception) {
            Log.e("SciChart", "Error when setting the license", e)
        }

    }
}