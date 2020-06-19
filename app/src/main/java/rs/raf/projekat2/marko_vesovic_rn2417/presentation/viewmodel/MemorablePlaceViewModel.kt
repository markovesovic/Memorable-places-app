package rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.data.repository.MemorablePlaceRepository
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract.MemorablePlaceContract
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState

class MemorablePlaceViewModel(
    private val repository: MemorablePlaceRepository
): ViewModel(), MemorablePlaceContract.ViewModel {

    override val memorablePlaceState = MutableLiveData<MemorablePlaceState>()

    private val subscriptions = CompositeDisposable()

    override fun getAllMemorablePlaces() {
        val subscription = repository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.Success(it)
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from database")
                }
            )
        subscriptions.add(subscription)
    }

    override fun insertMemorablePlace(memorablePlace: MemorablePlace) {
        val subscription = repository
            .insert(memorablePlace)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.SuccessMessage("Successfully inserted entity")
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while inserting entity")
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}