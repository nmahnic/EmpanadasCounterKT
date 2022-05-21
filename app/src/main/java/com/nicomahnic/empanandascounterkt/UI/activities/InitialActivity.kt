package com.nicomahnic.empanandascounterkt.UI.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nicomahnic.empanandascounterkt.databinding.ActivityInitialBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InitialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInitialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInitialBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnBegin.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }

}