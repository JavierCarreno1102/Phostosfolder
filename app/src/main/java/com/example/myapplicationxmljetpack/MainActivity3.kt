package com.example.myapplicationxmljetpack

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import coil.compose.rememberImagePainter
import com.example.myapplicationxmljetpack.ui.theme.MyApplicationXmlJetpackTheme
import java.io.File

class MainActivity3 : ComponentActivity() {

    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {


            MyApplicationXmlJetpackTheme {
               Button(onClick = { requestOpenCamera() }) {
                   Text(text = "Open Camera 2")
               }

                if(shouldShowPhoto.value){
//                    Image(
//                        painter = rememberImagePainter(uriFromCam),
//                        contentDescription = null,
//                        modifier = Modifier.fillMaxSize()
//                    )
                }


            }
        }
    }






    //primero
    private val REQUEST_CAMERA: Int = 201
    //para verificar si el usuario tiene permiso de la camara activado y si no pedirselos al usuario-----------
    private fun requestOpenCamera() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (checkSelfPermission(android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
            ) {

                val permissionCam = arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                requestPermissions(permissionCam, REQUEST_CAMERA)

            } else {
                Log.i("Test","Ya fue otorgado")
                openCamera()
            }
        } else {
            Log.i("Test","Ya fue otorgado")
            openCamera()
        }
    }

    //segundo---------
    //para detectar si el usuario acepto los permisos para la camara o el external storage
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {


            REQUEST_CAMERA -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Log.i("Test","permiso negado")
                }
            }
        }
    }





    private var uriFromCam: Uri? = null
    private fun openCamera() {

        val photoName= "photo"+System.currentTimeMillis()
        val photoFolder="InspectionApp"

        val value = ContentValues()
        value.put(MediaStore.Images.Media.DISPLAY_NAME, photoName)


        val uri = if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.Q){
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

        }else{
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }




        value.put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/$photoFolder/")
        val finalUri=contentResolver.insert(uri,value)
        uriFromCam=finalUri
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, finalUri)
        startActivityForResult(cameraIntent, REQUEST_CAMERA)
    }



    //para detectar la imagen seleccionada por el usuario-------------------
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CAMERA && resultCode == Activity.RESULT_OK) {
            shouldShowPhoto.value=true
         Log.i("Photo",uriFromCam.toString())
        }

    }


}



