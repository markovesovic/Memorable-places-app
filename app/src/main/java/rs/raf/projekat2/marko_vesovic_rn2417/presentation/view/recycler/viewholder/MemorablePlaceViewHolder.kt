package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.memorable_place_view_holder.*
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace

class MemorablePlaceViewHolder(
    override val containerView: View,
    private val onClickAction: (Int) -> Unit
): RecyclerView.ViewHolder(containerView), LayoutContainer {


    init {
        viewHolder.setOnClickListener {
            if(adapterPosition != RecyclerView.NO_POSITION) {
                onClickAction.invoke(adapterPosition)
            }
        }
    }

    fun bind(mp: MemorablePlace) {
        title.text = mp.title
        content.text = mp.content
        date.text = mp.dateAdded.toString()
    }

}