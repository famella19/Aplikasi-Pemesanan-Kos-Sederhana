package org.d3if3018.asessment1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import org.d3if3018.asessment1.databinding.ActivityMainArtikelBinding

class MainArtikel : AppCompatActivity() {

    private lateinit var binding: ActivityMainArtikelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainArtikelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCari.setOnClickListener { cariJenisPajak() }
        binding.btnHapus.setOnClickListener{ hapusPajak() }

    }

    private fun cariJenisPajak(){
        val jenisPajak = binding.inpPajak.text.toString()

        if (TextUtils.isEmpty(jenisPajak)){
            Toast.makeText(this, R.string.cariPajak, Toast.LENGTH_LONG).show()
        }
        if (TextUtils.isDigitsOnly(jenisPajak)) {
            Toast.makeText(this, R.string.inputAngka, Toast.LENGTH_LONG).show()
        }
        if (jenisPajak.equals(("Pajak Bumi dan Bangunan"), ignoreCase = true)) {
            binding.namaPajak.isVisible = true
            binding.deskripsi.isVisible = true
            binding.imgPajak.isVisible = true

            binding.namaPajak.text = getString(R.string.pajakPBB)
            binding.deskripsi.text = getString(R.string.pbb)
            binding.imgPajak.setImageResource(R.drawable.pbbb)
        }else if (jenisPajak.equals(("Pajak Penghasilan"), ignoreCase = true)){
            binding.namaPajak.isVisible = true
            binding.deskripsi.isVisible = true
            binding.imgPajak.isVisible = true

            binding.namaPajak.text = getString(R.string.pajakPPH)
            binding.deskripsi.text = getString(R.string.pph)
            binding.imgPajak.setImageResource(R.drawable.penghasilan)
        }else if (jenisPajak.equals(("Pajak Pertambahan Nilai"), ignoreCase = true)) {
            binding.namaPajak.isVisible = true
            binding.deskripsi.isVisible = true
            binding.imgPajak.isVisible = true

            binding.namaPajak.text = getString(R.string.pajakPPN)
            binding.deskripsi.text = getString(R.string.ppn)
            binding.imgPajak.setImageResource(R.drawable.ppn)
        }else if (jenisPajak.equals(("Pajak Penjualan Barang Mewah"), ignoreCase = true)) {
            binding.namaPajak.isVisible = true
            binding.deskripsi.isVisible = true
            binding.imgPajak.isVisible = true

            binding.namaPajak.text = getString(R.string.pajakPPNBM)
            binding.deskripsi.text = getString(R.string.ppnbm)
            binding.imgPajak.setImageResource(R.drawable.ppnbm)
        }else if (jenisPajak.equals(("Bea Materai"), ignoreCase = true)) {
            binding.namaPajak.isVisible = true
            binding.deskripsi.isVisible = true
            binding.imgPajak.isVisible = true

            binding.namaPajak.text = getString(R.string.pajakBM)
            binding.deskripsi.text = getString(R.string.bm)
            binding.imgPajak.setImageResource(R.drawable.bm)
        }else if (jenisPajak.equals(("Pajak Daerah"), ignoreCase = true)) {
            binding.namaPajak.isVisible = true
            binding.deskripsi.isVisible = true
            binding.imgPajak.isVisible = true

            binding.namaPajak.text = getString(R.string.pajakPAD)
            binding.deskripsi.text = getString(R.string.pad)
            binding.imgPajak.setImageResource(R.drawable.pad)
        }
    }

    private fun hapusPajak(){
        binding.inpPajak.text?.clear()
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
