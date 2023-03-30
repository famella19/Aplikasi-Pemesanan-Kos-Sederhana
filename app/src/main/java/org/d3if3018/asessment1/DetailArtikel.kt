package org.d3if3018.asessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.d3if3018.asessment1.databinding.ActivityDetailArtikelBinding

class DetailArtikel : AppCompatActivity() {

    private lateinit var binding: ActivityDetailArtikelBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}