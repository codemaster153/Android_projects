package com.lkb.baseandroidproject

data class QuizListModel(
    val question: String = "",
    val options: List<QuizItem> = listOf(),
    var selectedTag: Int = 0, var isLocked: Boolean = false,
)

data class QuizItem(val value: String = "", val isCorrect: Boolean = false, val tag: Int)


fun getData(): MutableList<QuizListModel> {
    var testData = mutableListOf<QuizListModel>()
    var quizItems = mutableListOf<QuizItem>()
    quizItems.add(QuizItem(value = "Delhi", isCorrect = true, tag = 1))
    quizItems.add(QuizItem(value = "Pune", isCorrect = false, tag = 2))
    quizItems.add(QuizItem(value = "Kolkata", isCorrect = false, tag = 3))
    quizItems.add(QuizItem(value = "Odisha", isCorrect = false, tag = 4))

    var quizItems2 = mutableListOf<QuizItem>()
    quizItems2.add(QuizItem(value = "Paris", isCorrect = true, tag = 1))
    quizItems2.add(QuizItem(value = "NY", isCorrect = false, tag = 2))
    quizItems2.add(QuizItem(value = "Kolkata", isCorrect = false, tag = 3))
    quizItems2.add(QuizItem(value = "Washington", isCorrect = false, tag = 4))

    testData.add(
        QuizListModel("What is the capital of India?",
            options = quizItems,
            selectedTag = 0,
            isLocked = false),
    )
    testData.add(
        QuizListModel("What is the capital of France?",
            options = quizItems2,
            selectedTag = 0,
            isLocked = false),
    )
    testData.addAll(testData)
    testData.addAll(testData)
    testData.add(
        QuizListModel("What is the capital of India?",
            options = quizItems,
            selectedTag = 0,
            isLocked = false),
    )
    testData.add(
        QuizListModel("What is the capital of France?",
            options = quizItems2,
            selectedTag = 0,
            isLocked = false),
    )
    return testData
}

