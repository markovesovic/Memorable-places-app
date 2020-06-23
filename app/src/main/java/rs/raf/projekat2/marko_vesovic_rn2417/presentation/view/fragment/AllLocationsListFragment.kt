package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_all_location_list.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract.MemorablePlaceContract
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.activity.EditLocationActivity
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.adapter.MemorablePlaceAdapter
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.diff.MemorablePlaceDiffItemCallback
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel.MemorablePlaceViewModel
import timber.log.Timber
import java.util.*

class AllLocationsListFragment: Fragment(R.layout.fragment_all_location_list) {

    companion object {
        const val EDIT_LOCATION_REQUEST_CODE = 0
    }


    private val memorablePlaceViewModel: MemorablePlaceContract.ViewModel by sharedViewModel<MemorablePlaceViewModel>()

    private lateinit var adapter: MemorablePlaceAdapter

    private var ascending = false
    private var filter = ""

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
        Timber.e("Poziva se edit funkcija")
        val intent = Intent(this.context, EditLocationActivity::class.java)
        intent.putExtra("title", it.title)
        intent.putExtra("content", it.content)
        intent.putExtra("dateAdded", it.dateAdded.time)
        intent.putExtra("lon", it.location.longitude)
        intent.putExtra("lat", it.location.latitude)
        intent.putExtra("id", it.id)
        startActivityForResult(intent, EDIT_LOCATION_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            EDIT_LOCATION_REQUEST_CODE -> {
                Timber.e("Poziva se on activity result")
                if(resultCode != Activity.RESULT_OK) return
                val id = data?.getLongExtra("id", 0)
                val title = data?.getStringExtra("title")
                val content = data?.getStringExtra("content")
                val dateAdded = data?.getLongExtra("dateAdded", 0)
                val lon = data?.getDoubleExtra("lat", 0.0)
                val lat = data?.getDoubleExtra("lon", 0.0)
                if(id != null && title != null && content != null && dateAdded != null && lon != null && lat != null) {
                    memorablePlaceViewModel.updateMemorablePlace(
                        MemorablePlace(
                            id = id,
                            title = title,
                            content = content,
                            location = LatLng(lat, lon),
                            dateAdded = Date(dateAdded)
                        )
                    )
                }
            }
        }
    }


    private fun initRecycler() {
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = MemorablePlaceAdapter(MemorablePlaceDiffItemCallback(), onViewHolderClicked)
        recyclerView.adapter = adapter
    }

    private fun initListeners() {
        fab.setOnClickListener {
            Timber.e("Poziva se iz faba, asc = $ascending")
            ascending = if (ascending) {
                fab.setImageResource(R.drawable.ic_baseline_expand_less_24)
                if (filter != "") {
                    memorablePlaceViewModel.getAllMemorablePlacesAscending("")
                    Timber.e("Ascending")
                }
                else {
                    memorablePlaceViewModel.getAllMemorablePlacesByFilterAscending(filter)
                    Timber.e("Ascending filter")
                }
                false
            } else {
                fab.setImageResource(R.drawable.ic_baseline_expand_more_24)
                if (filter != "") {
                    memorablePlaceViewModel.getAllMemorablePlacesDescending("")
                    Timber.e("Descending")
                }
                else {
                    memorablePlaceViewModel.getAllMemorablePlacesByFilterDescending(filter)
                    Timber.e("Descending filter")
                }
                true
            }
        }


        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText == null) return false
//                if(newText != "") filter = newText
//                else return false
                Timber.e("Poziva se iz searchbara, query = $newText")
                if(ascending) {
                    memorablePlaceViewModel.getAllMemorablePlacesByFilterAscending(filter)
                } else {
                    memorablePlaceViewModel.getAllMemorablePlacesByFilterDescending(filter)
                }
                return false
            }
        })
    }

    private fun initObservers() {
        memorablePlaceViewModel.memorablePlaceState.observe(this.viewLifecycleOwner, Observer {
            renderState(it)
            Timber.e("Iz observera se poziva ovo, lista se updateovala")
        })
        memorablePlaceViewModel.getAllMemorablePlacesDescending("")
//        memorablePlaceViewModel.deleteAll()
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