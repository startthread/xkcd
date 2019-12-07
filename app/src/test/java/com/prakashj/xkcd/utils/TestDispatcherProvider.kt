package com.prakashj.xkcd.utils

import com.prakashj.xkcd.infra.DispatcherProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

open class TestDispatcherProvider : DispatcherProvider() {
    override val Main: CoroutineContext = Dispatchers.Unconfined
    override val IO: CoroutineContext = Dispatchers.Unconfined
}
