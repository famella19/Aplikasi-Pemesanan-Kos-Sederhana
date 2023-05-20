package org.d3if3018.asessment1.ui

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.databinding.FragmentPerhitunganPajakBinding
import org.d3if3018.asessment1.db.PajakDb
import org.d3if3018.asessment1.model.HasilPajak
import org.d3if3018.asessment1.ui.History.HistoriAdapter
import org.d3if3018.asessment1.ui.HitungPajak.HitungPajakViewModelFactory
import org.d3if3018.asessment1.ui.HitungPajak.HitungViewModel

class PerhitunganPajakFragment : Fragment() {

    private lateinit var binding: FragmentPerhitunganPajakBinding
    private lateinit var myAdapter: HistoriAdapter

    private val viewModel: HitungViewModel by lazy {
        val db = PajakDb.getInstance(requireContext())
        val factory = HitungPajakViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[HitungViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPerhitunganPajakBinding.inflate(layoutInflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        myAdapter = HistoriAdapter()

        binding.btnHitung.setOnClickListener {hitungPajak() }
        binding.btnReset.setOnClickListener {ressetPajak()}
        binding.shareButton.setOnClickListener{shareData()}

        viewModel.getHasilHitung().observe(requireActivity()) { showResult(it) }

        setHasOptionsMenu(true)
//        viewModel.getNavigasi .observe(viewLifecycleOwner, {
//            if (it == null) return@observe
//            Log.d("PerhitunganPajakFragment", "Data tersimpan. ID = ${it.id}")
//        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_perhitunganPajakFragment_to_historyFragment2)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_perhitunganPajakFragment_to_aboutUs)
                return true
            }
            R.id.menu_artikel -> {
                findNavController().navigate(
                    R.id.action_perhitunganPajakFragment_to_artikelFragment)
                return true
            }
            R.id.menu_info -> {
                findNavController().navigate(
                    R.id.action_perhitunganPajakFragment_to_detailHitungFragment)
                return true
            }
        }
            return super.onOptionsItemSelected(item)
    }

    private fun hitungPajak() {
        val luasTanah = binding.inpLuasTanah.text.toString()
        if (TextUtils.isEmpty(luasTanah)) {
            Toast.makeText(context, R.string.luasTanah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val nilaiJualTanah = binding.inpNilJualTnh.text.toString()
        if (TextUtils.isEmpty(nilaiJualTanah)) {
            Toast.makeText(context, R.string.nilaiJualTanah_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val luasBangunan = binding.inpLuasBangunan.text.toString()
        if (TextUtils.isEmpty(luasBangunan)) {
            Toast.makeText(context, R.string.luasBangunan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        val nilaiJualBangunan = binding.inpNilJualBang.text.toString()
        if (TextUtils.isEmpty(nilaiJualBangunan)) {
            Toast.makeText(context, R.string.nilaiJualBangunan_invalid, Toast.LENGTH_LONG).show()
            return
        }

        viewModel.hitungPajak(
            luasTanah.toDouble(),
            nilaiJualTanah.toDouble(),
            luasBangunan.toDouble(),
            nilaiJualBangunan.toDouble()
        )
    }

    private fun shareData() {
        val message = getString(R.string.teksBagi)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager
            ) != null
        ) {
            startActivity(shareIntent)
        }
    }

    private fun showResult(result: HasilPajak?){
        if (result == null) return

        binding.teksNJKP.text = getString(R.string.njkp_x, result.njkp)
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

//    override fun onBackPressed() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle(R.string.app_name)
//        builder.setMessage(R.string.keluar)
//        builder.setPositiveButton("Ya") { _, _ ->
//            this.finish()
//        }
//        builder.setNegativeButton("Tidak") { _, _ -> }
//        builder.setCancelable(false)
//        builder.show()
//    }

}

