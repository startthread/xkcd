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
        val comic = Comic()
        val response: Response<Comic> = Response.success(comic)

        `when`(apiService.getCurrentComic()).thenReturn(response)

        viewModel.getCurrentComic()

        assertThat(viewModel.comicLiveData.value).isEqualTo(comic)
    }

    @Test
    fun onNextComicClick() = coroutineTestRule.runBlockingTest {
        val currentComic = Comic(num = 100)
        viewModel.comicLiveData.value = currentComic

        val nextComic = Comic(num = 101)
        val response: Response<Comic> = Response.success(nextComic)

        `when`(apiService.getComic(currentComic.num + 1)).thenReturn(response)

        viewModel.onNextComicClick()

        assertThat(viewModel.comicLiveData.value).isEqualTo(nextComic)
    }

    @Test
    fun onPrevComicClick() = coroutineTestRule.runBlockingTest {
        val currentComic = Comic(num = 100)
        viewModel.comicLiveData.value = currentComic

        val prevComic = Comic(num = 99)
        val response: Response<Comic> = Response.success(prevComic)

        `when`(apiService.getComic(currentComic.num - 1)).thenReturn(response)

        viewModel.onPrevComicClick()

        assertThat(viewModel.comicLiveData.value).isEqualTo(prevComic)
    }
}
