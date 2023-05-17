package org.d3if3018.asessment1.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import org.d3if3018.asessment1.MainArtikel
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.databinding.ActivityPerhitunganPajakBinding
import org.d3if3018.asessment1.model.HasilPajak
import org.d3if3018.asessment1.model.MainViewModel

class PerhitunganPajak : AppCompatActivity() {

    private lateinit var binding: ActivityPerhitunganPajakBinding
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPerhitunganPajakBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHitung.setOnClickListener {hitungPajak() }
        viewModel.getHasilHitung().observe(this,{showResult(it)})

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

        viewModel.hitungPajak(
            luasTanah.toDouble(),
            nilaiJualTanah.toDouble(),
            luasBangunan.toDouble(),
            nilaiJualBangunan.toDouble()
        )
    }

    private fun showResult(result: HasilPajak?){
        if (result == null) return

        binding.teksNJKP.text = getString(R.string.njkp_x, result.njkp)
//        binding.teksNJKP.text = result.njkp.toDouble()
        binding.teksPajak.text = getString(R.string.pajak_x, result.pajak)
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

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.app_name)
        builder.setMessage(R.string.keluar)
        builder.setPositiveButton("Ya") { _, _ ->
            this.finish()
        }
        builder.setNegativeButton("Tidak") { _, _ -> }
        builder.setCancelable(false)
        builder.show()
    }

}

