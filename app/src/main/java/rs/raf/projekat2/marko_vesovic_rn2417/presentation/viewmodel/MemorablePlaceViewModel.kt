package rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.marko_vesovic_rn2417.data.model.MemorablePlace
import rs.raf.projekat2.marko_vesovic_rn2417.data.repository.MemorablePlaceRepository
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.contract.MemorablePlaceContract
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.view.state.MemorablePlaceState
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MemorablePlaceViewModel(
    private val repository: MemorablePlaceRepository
): ViewModel(), MemorablePlaceContract.ViewModel {

    override val memorablePlaceState = MutableLiveData<MemorablePlaceState>()

    private val subscriptions = CompositeDisposable()

    private val publishSubjectFilterAsc: PublishSubject<String> = PublishSubject.create()
    private val publishSubjectFilterDesc: PublishSubject<String> = PublishSubject.create()
    private val publishSubjectAsc: PublishSubject<String> = PublishSubject.create()
    private val publishSubjectDesc: PublishSubject<String> = PublishSubject.create()

    init {
        val subscriptionFilterAsc = publishSubjectFilterAsc
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                repository
                    .getAllByFilterAscending(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                    }
            }
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.Success(it)
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from db")
                }
            )
        val subscriptionFilterDesc = publishSubjectFilterDesc
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                repository
                    .getAllByFilterDescending(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                    }
            }
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.Success(it)
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from db")
                }
            )
        val subscriptionAsc = publishSubjectAsc
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                repository
                    .getAllAscending()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                    }
            }
            .subscribe (
                {
                    memorablePlaceState.value = MemorablePlaceState.Success(it)
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from db")
                }
            )
        val subscriptionDesc = publishSubjectDesc
            .debounce(100, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap {
                repository
                    .getAllDescending()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                    }
            }
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.Success(it)
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from db")
                }
            )
        subscriptions.add(subscriptionFilterAsc)
        subscriptions.add(subscriptionFilterDesc)
        subscriptions.add(subscriptionAsc)
        subscriptions.add(subscriptionDesc)
    }

    override fun getAllMemorablePlacesAscending(s: String) {
        publishSubjectAsc.onNext(s)

//        val subscription = repository
//            .getAllAscending()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    memorablePlaceState.value = MemorablePlaceState.Success(it)
//                },
//                {
//                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from database")
//                }
//            )
//        subscriptions.add(subscription)
    }
    override fun getAllMemorablePlacesDescending(s: String) {
        publishSubjectDesc.onNext(s)
//        val subscription = repository
//            .getAllAscending()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                {
//                    memorablePlaceState.value = MemorablePlaceState.Success(it)
//                },
//                {
//                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while getting data from database")
//                }
//            )
//        subscriptions.add(subscription)
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

    override fun updateMemorablePlace(memorablePlace: MemorablePlace) {
        val subscription = repository
            .update(memorablePlace)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.SuccessMessage("Successfully updated location")
                },
                {
                    memorablePlaceState.value = MemorablePlaceState.Error("Error happened while updating location")
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllMemorablePlacesByFilterAscending(filter: String) {
        publishSubjectFilterAsc.onNext(filter)
    }

    override fun getAllMemorablePlacesByFilterDescending(filter: String) {
        publishSubjectFilterDesc.onNext(filter)
    }

    override fun deleteMemorablePlace(memorablePlace: MemorablePlace) {
        val subscription = repository
            .delete(memorablePlace)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.SuccessMessage("Successfully deleted location")
                },
                {

                }
            )
        subscriptions.add(subscription)
    }

    override fun deleteAll() {
        val subscription = repository
            .deleteAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    memorablePlaceState.value = MemorablePlaceState.SuccessMessage("Successfully deleted all locations")
                },
                {

                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}