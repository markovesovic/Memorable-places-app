package rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace

class MemorablePlaceDiffItemCallback: DiffUtil.ItemCallback<MemorablePlace>() {
    override fun areItemsTheSame(oldItem: MemorablePlace, newItem: MemorablePlace): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MemorablePlace, newItem: MemorablePlace): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.title == newItem.title &&
                oldItem.content == newItem.content &&
                oldItem.dateAdded == newItem.dateAdded
    }
}