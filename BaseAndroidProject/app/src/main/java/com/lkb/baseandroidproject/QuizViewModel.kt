package com.lkb.baseandroidproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModel : ViewModel() {
    var totalScore = 0
    var currentIndex = 0
    var text = "Are you ready?"
    private val _uiState =
        MutableStateFlow<QuizState<QuizListModel>>(QuizState.Initial(QuizListModel()))
    val uiState: StateFlow<QuizState<QuizListModel>> = _uiState

    init {
        _uiState.value = QuizState.Initial(getData()[0])
    }


    fun evaluate(quiz: QuizItem) {
        if (currentIndex == 9) {
            if (quiz.isCorrect) {
                totalScore += 1
            }
            _uiState.value = QuizState.End
            return
        } else {
            val data = getData()[currentIndex]
            data.selectedTag = quiz.tag
            data.isLocked = true

            if (quiz.isCorrect) {
                totalScore += 1
                _uiState.value = QuizState.Correct(data)
            } else {
                _uiState.value = QuizState.Incorrect(data)
            }
            viewModelScope.launch {
                delay(500L)
                currentIndex++
                _uiState.value = QuizState.Initial(getData()[currentIndex])
            }
        }
    }

    fun reset() {
        totalScore = 0
        currentIndex = 0
        _uiState.value = QuizState.Initial(getData()[currentIndex])
    }


}