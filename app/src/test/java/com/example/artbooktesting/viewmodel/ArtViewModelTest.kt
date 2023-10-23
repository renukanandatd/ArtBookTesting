package com.example.artbooktesting.viewmodel

import com.example.artbooktesting.repo.FakeArtRepository
import com.google.common.truth.ExpectFailure.assertThat
import org.junit.Before
import org.junit.Test

class ArtViewModelTest {

    private lateinit var viewModel : ArtViewModel

    @Before
    fun setup() {
        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`() {
        viewModel.makeArt("Mona Lisa","Da Vinci","")

        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }


    @Test
    fun `insert art without name returns error`() {
        viewModel.makeArt("","Da Vinci","1500")

        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error`() {
        viewModel.makeArt("Mona Lisa","","1500")

        val value = viewModel.insertArtMessage.getOrAwaitValueTest()

        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}