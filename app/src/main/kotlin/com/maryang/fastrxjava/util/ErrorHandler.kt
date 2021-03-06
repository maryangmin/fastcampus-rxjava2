package com.maryang.fastrxjava.util

import com.maryang.fastrxjava.BuildConfig
import com.maryang.fastrxjava.event.LogoutEvent
import com.maryang.fastrxjava.event.RxBus
import retrofit2.HttpException


object ErrorHandler {

    fun handle(t: Throwable) {
        if (BuildConfig.DEBUG) {
            t.printStackTrace()
            // sendCrash(t)
        }

        if (t is HttpException) {
            // 401 익셉션은 토큰이 만료된 애라고 약속을 했다
            if (t.code() == 401) {
                RxBus.post(LogoutEvent())
            }
        }
    }
}
