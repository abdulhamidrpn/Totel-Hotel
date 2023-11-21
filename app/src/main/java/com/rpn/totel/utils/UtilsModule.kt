package com.rpn.totel.utils

import org.koin.dsl.module

val utilsModule = module {
    factory { SettingsUtility(get()) }
}