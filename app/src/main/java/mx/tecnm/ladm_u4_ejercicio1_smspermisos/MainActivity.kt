package mx.tecnm.ladm_u4_ejercicio1_smspermisos

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val siPermiso = 1
    val siPermisoReceiver = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.RECEIVE_SMS)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.RECEIVE_SMS),siPermisoReceiver)
        }

        button.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        arrayOf(android.Manifest.permission.SEND_SMS), siPermiso)
            }else{
                envioSMS()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==siPermiso){
            envioSMS()
        }
        if (requestCode == siPermisoReceiver){
            mensajeRecibir()
        }
    }

    private fun mensajeRecibir() {
        AlertDialog.Builder(this)
            .setMessage("Se otorgo Permiso recibir")
            .show()
    }

    private fun envioSMS() {
        SmsManager.getDefault().sendTextMessage(editTextPhone.text.toString(),null,"Prueba desde codigo uauaua",null,null)
        Toast.makeText(this,editTextTextPersonName.text.toString(),Toast.LENGTH_LONG).show()
    }
}