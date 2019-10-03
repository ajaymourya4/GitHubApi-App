package com.ajaymourya.githubapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajaymourya.githubapi.adapters.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        setSupportActionBar(result_toolbar)

        val userId:String = intent.getStringExtra("userId")

       
    }
}
