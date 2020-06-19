package rs.raf.projekat2.marko_vesovic_rn2417.app

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.marko_vesovic_rn2417.module.coreModule
import rs.raf.projekat2.marko_vesovic_rn2417.module.memorablePlaceModule
import timber.log.Timber

class MemorablePlacesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
        initStetho()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            memorablePlaceModule
        )
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MemorablePlacesApplication)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }

}