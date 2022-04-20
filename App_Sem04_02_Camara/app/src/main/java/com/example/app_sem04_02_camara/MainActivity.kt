package com.example.app_sem04_02_camara

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btRequest = findViewById<Button>(R.id.btRequest)

        btRequest.setOnClickListener {
            checkCameraPermission()
        }
    }

    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED
        ) {
            //permiso rechazado
            requestCameraPermission()
        } else {
            Toast.makeText(this, "Ya se cuenta con permiso a la camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
        {
            //el usuario ya rechazo el permiso. Ir a ajustes
            Toast.makeText(this, "Rechazo el permiso antes. Ir a ajustes", Toast.LENGTH_LONG).show()
        }
        else{
            //el usuario nunca aceptó ni  rechazo el permiso. Solicitamos el permiso
            Toast.makeText(this, "Debe aceptar el permiso", Toast.LENGTH_LONG).show()
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
        }
    }

    //METODO para escuchar la rpta
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //permiso aceptado
                    Toast.makeText(this, "Permiso aceptado", Toast.LENGTH_LONG).show()
                    //Aqui llamo a la funcionalidad
                }
                else{
                    Toast.makeText(this, "Permiso negado", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}