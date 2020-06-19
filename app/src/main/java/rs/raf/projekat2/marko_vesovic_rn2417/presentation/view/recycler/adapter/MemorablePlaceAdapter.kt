package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.projekat2.marko_vesovic_rn2417.R
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.diff.MemorablePlaceDiffItemCallback
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.viewholder.MemorablePlaceViewHolder

class MemorablePlaceAdapter(
    private val memorablePlaceDiffItemCallback: MemorablePlaceDiffItemCallback,
    private val onClickAction: (MemorablePlace) -> Unit
): ListAdapter<MemorablePlace, MemorablePlaceViewHolder>(memorablePlaceDiffItemCallback) {

    private val onClickActionLocal: (Int) -> Unit = {
        onClickAction.invoke(getItem(it))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemorablePlaceViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.memorable_place_view_holder, parent, false)
        return MemorablePlaceViewHolder(containerView, onClickActionLocal)
    }

    override fun onBindViewHolder(holder: MemorablePlaceViewHolder, position: Int) {
        val mp = getItem(position)
        holder.bind(mp)
    }
}