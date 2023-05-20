package org.d3if3018.asessment1.ui.Artikel

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if3018.asessment1.R
import org.d3if3018.asessment1.data.SettingsDataStore
import org.d3if3018.asessment1.data.dataStore
import org.d3if3018.asessment1.databinding.FragmentArtikelBinding

class ArtikelFragment : Fragment() {

    private val layoutDataStore: SettingsDataStore by lazy {
        SettingsDataStore(requireContext().dataStore)
    }

    private val viewModel: ArtikelViewModel by  lazy {
        ViewModelProvider(this)[ArtikelViewModel::class.java]
    }

    private lateinit var myAdapter: ArtikelAdapter
    private lateinit var binding: FragmentArtikelBinding
    private var isLineraLayout = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtikelBinding.inflate(layoutInflater, container,false)
        myAdapter = ArtikelAdapter()
        with(binding.recyclerView){
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner){
            isLineraLayout = it
            setLayout()
            activity?.invalidateOptionsMenu()
        }

        viewModel.getData().observe(viewLifecycleOwner){
            myAdapter.updateData(it)
        }
    }

    private fun setLayout(){
        binding.recyclerView.layoutManager = if (isLineraLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, 2)
    }

    private fun setIcon(menuItem: MenuItem) {
        val iconId = if (isLineraLayout)
            R.drawable.baseline_grid_view_24
        else
            R.drawable.baseline_view_list_24
        menuItem.icon = ContextCompat.getDrawable(requireContext(), iconId)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.artikel_menu, menu)
        val menuItem = menu.findItem(R.id.action_switch_layout)
        setIcon(menuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_switch_layout) {
            lifecycleScope.launch {
                layoutDataStore.saveLayout(!isLineraLayout, requireContext())
            }
            return true
        }
        return super.onContextItemSelected(item)
    }
}

