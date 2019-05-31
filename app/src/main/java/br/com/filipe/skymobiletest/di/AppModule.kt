package br.com.filipe.skymobiletest.di

import com.google.gson.Gson
import org.koin.dsl.module.module

/**
 * Created by Murilo Moro on 11/02/19.
 */

val appModule = module {

    single { Gson() }

}