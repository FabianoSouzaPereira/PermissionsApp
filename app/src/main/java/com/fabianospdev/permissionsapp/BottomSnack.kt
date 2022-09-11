package com.fabianospdev.permissionsapp

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.google.android.material.bottomsheet.BottomSheetDialog

class BottomSnack(var context: Context, var permission: String) {

    private var mDialogview: View? = null
    private lateinit var dialog: BottomSheetDialog
    private lateinit var title: com.google.android.material.textview.MaterialTextView
    private lateinit var message: com.google.android.material.textview.MaterialTextView
    private lateinit var btnOk: com.google.android.material.button.MaterialButton
    private lateinit var btnCancel: com.google.android.material.button.MaterialButton


    init {
        startBootomDialog(context, permission)
    }

    private fun startBootomDialog(context: Context, permission: String){
        try {
            var permissionText = ""

            when (permission){
                Manifest.permission.INTERNET -> {
                    permissionText = "Permission to access Internet"
                }
                Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                    permissionText = "Permission to write in Files and Media"
                }
                Manifest.permission.READ_EXTERNAL_STORAGE -> {
                    permissionText = "Permission to read Files and Media"
                }
                Manifest.permission.ACCESS_FINE_LOCATION -> {
                    permissionText = "Permission to access Fine Location"
                }
                Manifest.permission.ACCESS_COARSE_LOCATION -> {
                    permissionText = "Permission to access Coarse Location"
                }
            }
            val messageText = permissionText

            val inflater: LayoutInflater = LayoutInflater.from(context as Activity)
            dialog = BottomSheetDialog(context)
            mDialogview = inflater.inflate(R.layout.bottomsnack, null)
            dialog.setContentView(mDialogview as View)
            title = dialog.findViewById(R.id.txtTitle)!!
            title.text = context.resources.getString(R.string.title_permission)
            message = dialog.findViewById(R.id.txtMessage)!!
            message.text = messageText
            btnOk = dialog.findViewById(R.id.btnOk)!!
            btnOk.text = context.resources.getString(R.string.snackbar_aceitar)
            btnCancel = dialog.findViewById(R.id.bntCancel)!!
            btnCancel.text = context.resources.getString(R.string.snackbar_cancel)
            dialog.show()

            dialog.findViewById<com.google.android.material.button.MaterialButton>(R.id.btnOk)?.setOnClickListener {
                startActivity(context,
                    Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + BuildConfig.APPLICATION_ID)
                    ), null
                )
                dialog.dismiss()
            }

            dialog.findViewById<com.google.android.material.button.MaterialButton>(R.id.bntCancel)?.setOnClickListener {
                dialog.dismiss()
            }

        } catch (e: Exception) {
            print(e)
        }
    }
}