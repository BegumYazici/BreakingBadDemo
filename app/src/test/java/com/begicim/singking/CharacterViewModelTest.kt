package com.begicim.singking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.begicim.singking.network.CharacterApiService
import com.begicim.singking.network.CharacterModel
import com.begicim.singking.ui.character.ApiStatus
import com.begicim.singking.ui.character.CharacterViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito
import org.mockito.Mockito.verify
import java.lang.IllegalArgumentException

class CharacterViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var characterApiService = Mockito.mock(CharacterApiService::class.java)

    private val dispatcher = TestCoroutineDispatcher()

    private val characterViewModel = CharacterViewModel(characterApiService, dispatcher)

    @Test
    fun `when getCharacters() is called api service should be called`() = runBlockingTest {
        characterViewModel.getCharacters()

        verify(characterApiService).getCharacterList()
    }

    @Test
    fun `when getCharacters() return data api status should be Success`() = runBlockingTest {

        val dummyServiceResponse =
            listOf(CharacterModel(1, "Walter", "1.1.1963", "", "", ""))

        given(characterApiService.getCharacterList()).willReturn(CompletableDeferred(dummyServiceResponse))

        characterViewModel.getCharacters()

        Assert.assertEquals(ApiStatus.SUCCESS, characterViewModel.status.value)
    }

    @Test
    fun `when getCharacters got an error api status should be error`(){

        characterViewModel.getCharacters()

        given(characterApiService.getCharacterList()).willThrow(IllegalArgumentException::class.java)

        Assert.assertEquals(ApiStatus.ERROR, characterViewModel.status.value)

    }

    @After
    fun tearDown() {
        dispatcher.cleanupTestCoroutines()
    }
}