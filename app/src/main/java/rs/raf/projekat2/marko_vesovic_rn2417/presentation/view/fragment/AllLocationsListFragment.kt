package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_all_location_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract.MemorablePlaceContract
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.adapter.MemorablePlaceAdapter
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.diff.MemorablePlaceDiffItemCallback
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel.MemorablePlaceViewModel
import timber.log.Timber

class AllLocationsListFragment: Fragment(R.layout.fragment_all_location_list) {

    private val memorablePlaceViewModel: MemorablePlaceContract.ViewModel by sharedViewModel<MemorablePlaceViewModel>()

    private lateinit var adapter: MemorablePlaceAdapter

    private var ascending = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initRecycler()
        initObservers()
        initListeners()
    }

    private val onViewHolderClicked: (MemorablePlace) -> Unit = {

    }

    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = MemorablePlaceAdapter(MemorablePlaceDiffItemCallback(), onViewHolderClicked)
        recyclerView.adapter = adapter
    }

    private fun initListeners() {
        fab.setOnClickListener {
            ascending = if (ascending) {
                fab.setImageResource(R.drawable.ic_baseline_expand_less_24)
                false
            } else {
                fab.setImageResource(R.drawable.ic_baseline_expand_more_24)
                true
            }
        }


        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun initObservers() {
        memorablePlaceViewModel.memorablePlaceState.observe(this.viewLifecycleOwner, Observer {
            renderState(it)
            Timber.e("Iz observera se poziva ovo")
        })
        memorablePlaceViewModel.getAllMemorablePlacesDescending()
    }

    private fun renderState(state: MemorablePlaceState) {
        when(state) {
            is MemorablePlaceState.Success -> {
                adapter.submitList(state.memorablePlaces)
            }
            is MemorablePlaceState.Error -> {
                Toast.makeText(this.context, "Error happened", Toast.LENGTH_LONG).show()
            }
        }
    }
}