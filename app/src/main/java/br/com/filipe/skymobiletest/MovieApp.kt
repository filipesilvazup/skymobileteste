package br.com.filipe.skymobiletest

import android.app.Application
import br.com.filipe.skymobiletest.di.appModule
import br.com.filipe.skymobiletest.di.dataModule
import br.com.filipe.skymobiletest.di.domainModule
import br.com.filipe.skymobiletest.di.presentationModule
import org.koin.android.ext.android.startKoin

class MovieApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(
            androidContext = this,
            modules = listOf(
                appModule,
                dataModule,
                domainModule,
                presentationModule
            ),
            loadPropertiesFromFile = true
        )
    }
}