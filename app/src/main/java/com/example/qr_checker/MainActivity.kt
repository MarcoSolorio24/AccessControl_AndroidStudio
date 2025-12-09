package com.example.qr_checker

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.ScanContract
import com.journeyapps.barcodescanner.ScanOptions

class MainActivity : AppCompatActivity() {

    lateinit var ScanResultTv: TextView
    lateinit var scanBtn : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ScanResultTv = findViewById(R.id.Scanned_Result)
        scanBtn = findViewById(R.id.scanBtn)

        scanBtn.setOnClickListener {
            val options = ScanOptions()
            options.setPrompt("Scan Any Qr Code")
            options.setBeepEnabled(true)
            options.setOrientationLocked(true)
            options.setCaptureActivity(CaptureActivity::class.java)

            barcodeLauncher.launch(options)

        }
    }

    private var barcodeLauncher = registerForActivityResult(ScanContract()){ result ->
        if(result.contents != null){
            ScanResultTv.text = "Scanned Result: ${result.contents}"

            val url = result.contents

            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(browserIntent)

            }catch (e: Exception){
                Toast.makeText(this, "Cannot open the url: $url", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }else{
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()

        }
    }
}
