package rs.raf.projekat2.marko_vesovic_rn2417.module

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.marko_vesovic_rn2417.data.db.MemorablePlaceDatabase
import rs.raf.projekat2.marko_vesovic_rn2417.data.repository.MemorablePlaceRepository
import rs.raf.projekat2.marko_vesovic_rn2417.data.repository.MemorablePlaceRepositoryImplementation
import rs.raf.projekat2.marko_vesovic_rn2417.presentation.viewmodel.MemorablePlaceViewModel

val memorablePlaceModule = module {
    viewModel { MemorablePlaceViewModel(get()) }

    single<MemorablePlaceRepository> { MemorablePlaceRepositoryImplementation(get()) }

    single { get<MemorablePlaceDatabase>().getMemorablePlaceDao() }
}