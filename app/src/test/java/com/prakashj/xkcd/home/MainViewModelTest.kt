package com.prakashj.xkcd.home

import com.google.common.truth.Truth.assertThat
import com.prakashj.xkcd.infra.network.ApiService
import com.prakashj.xkcd.infra.network.Comic
import com.prakashj.xkcd.utils.CoroutineTestRule
import com.prakashj.xkcd.utils.LiveDataTestRule
import com.prakashj.xkcd.utils.TestDispatcherProvider
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class MainViewModelTest {

    @get:Rule
    val liveDataTestRule = LiveDataTestRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var apiService: ApiService

    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = MainViewModel(apiService, TestDispatcherProvider())
    }

    @Test
    fun testGetCurrentComic_onSuccess_postCurrentComic() = coroutineTestRule.runBlockingTest {
        val comic = mock(Comic::class.java)
        val response: Response<Comic> = Response.success(comic)

        `when`(apiService.getCurrentComic()).thenReturn(response)

        viewModel.getCurrentComic()

        assertThat(viewModel.comicLiveData.value).isEqualTo(comic)
    }

    @Test
    fun onNextComicClick() {
    }

    @Test
    fun onPrevComicClick() {
    }
}