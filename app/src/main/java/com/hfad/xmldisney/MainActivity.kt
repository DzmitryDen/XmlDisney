package com.hfad.xmldisney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hfad.xmldisney.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var binging: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binging = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binging?.root)
    }
}