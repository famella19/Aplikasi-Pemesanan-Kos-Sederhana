package org.d3if3018.asessment1

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.d3if3018.asessment1.databinding.ActivityPerhitunganPajakBinding

class PerhitunganPajak : AppCompatActivity() {

    private lateinit var binding: ActivityPerhitunganPajakBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerhitunganPajakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener {hitungPajak() }
        binding.btnReset.setOnClickListener {ressetPajak()}
        binding.news.setOnClickListener{artikel()}
}

    private fun hitungPajak() {
        val luasTanah = binding.inpLuasTanah.text.toString()
        if (TextUtils.isEmpty(luasTanah)) {
            Toast.makeText(this, R.string.luasTanah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val nilaiJualTanah = binding.inpNilJualTnh.text.toString()
        if (TextUtils.isEmpty(nilaiJualTanah)) {
            Toast.makeText(this, R.string.nilaiJualTanah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val luasBangunan = binding.inpLuasBangunan.text.toString()
        if (TextUtils.isEmpty(luasBangunan)) {
            Toast.makeText(this, R.string.luasBangunan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val nilaiJualBangunan = binding.inpNilJualBang.text.toString()
        if (TextUtils.isEmpty(nilaiJualBangunan)) {
            Toast.makeText(this, R.string.nilaiJualBangunan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val njkp = 0.20 * ((luasTanah.toInt() * nilaiJualTanah.toInt()) + (luasBangunan.toInt() * nilaiJualBangunan.toInt()))
        val pajak = 0.005 * njkp

        binding.teksNJKP.text = ("NJKP: " + njkp / 1000000 + "Juta")
        binding.teksPajak.text = ("Pajak : " + pajak)
    }

        private fun ressetPajak(){
            binding.inpLuasTanah.text!!.clear()
            binding.inpLuasBangunan.text!!.clear()
            binding.inpNilJualTnh.text!!.clear()
            binding.inpNilJualBang.text!!.clear()
            binding.teksNJKP.setText(" ")
            binding.teksPajak.setText(" ")
        }

        fun artikel() {
        val intent = Intent(this, MainArtikel::class.java)
        startActivity(intent)
    }

}

