package com.hfad.xmldisney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hfad.xmldisney.databinding.ActivityMainBinding
import com.hfad.xmldisney.ui.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var binging: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binging = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binging?.root)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, HomeFragment())
                .commit()
        }
    }

}