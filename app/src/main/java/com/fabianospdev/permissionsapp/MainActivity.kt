package com.fabianospdev.permissionsapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var layout: ConstraintLayout
    lateinit var context: Context
    private val permissions = arrayListOf(
        Manifest.permission.INTERNET,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION)

    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
        isGranted: Boolean ->
        if(isGranted){
            print("Permission: Granted")
        }else{
            print("Permission: NOT Granted")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        layout = findViewById(R.id.mainlayout)

    }

    override fun onResume() {
        super.onResume()
        permissions.forEach {
            requestPermission(it)
        }
    }

    private  fun requestPermission(permission: String){
        when {
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED -> {
                print("\n\npermission granted\n\n")
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this, permission) -> {
                print("\n\npermission denied\n\n")
                BottomSnack(context, permission)
            }
            else -> {
                print("\n\npermission not ask yet\n\n")
                requestPermissionLauncher.launch(permission)
            }
        }
    }
}