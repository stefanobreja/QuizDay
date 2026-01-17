package com.obi.quizday.data.quizzes

import com.obi.quizday.data.Response
import com.obi.quizday.data.Response.Success
import com.obi.quizday.domain.quizzez.QuizRepository
import com.obi.quizday.domain.quizzez.model.Answer
import com.obi.quizday.domain.quizzez.model.Category
import com.obi.quizday.domain.quizzez.model.Difficulty
import com.obi.quizday.domain.quizzez.model.Quiz
import com.obi.quizday.domain.quizzez.model.QuizType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object QuizRepositoryMockImpl : QuizRepository {
    val quizList = listOf(
        Quiz(
            category = "Animals",
            difficulty = Difficulty.EASY,
            question = "How many legs do butterflies have?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("6", true),
                Answer("2", false),
                Answer("4", false),
                Answer("0", false)
            )
        ),
        Quiz(
            category = "Entertainment: Video Games",
            difficulty = Difficulty.MEDIUM,
            question = "How many normal endings are there in Cry Of Fear's campaign mode?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("4", true),
                Answer("5", false),
                Answer("3", false),
                Answer("6", false)
            )
        ),
        Quiz(
            category = "Entertainment: Video Games",
            difficulty = Difficulty.MEDIUM,
            question = "Amazon acquired Twitch in August 2014 for $970 million dollars.",
            type = QuizType.BOOLEAN,
            answers = listOf(
                Answer("True", true),
                Answer("False", false)
            )
        ),
        Quiz(
            category = "Mythology",
            difficulty = Difficulty.HARD,
            question = "Talos, the mythical giant bronze man, was the protector of which island?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Crete", true),
                Answer("Sardinia", false),
                Answer("Sicily", false),
                Answer("Cyprus", false)
            )
        ),
        Quiz(
            category = "Science: Gadgets",
            difficulty = Difficulty.EASY,
            question = "The communication protocol NFC stands for Near-Field Control.",
            type = QuizType.BOOLEAN,
            answers = listOf(
                Answer("False", true),
                Answer("True", false)
            )
        ),
        Quiz(
            category = "Entertainment: Film",
            difficulty = Difficulty.HARD,
            question = "What three movies, in order from release date, make up the \"Dollars Trilogy\"?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("A Fistful of Dollars, For a Few Dollars More, The Good, the Bad, and the Ugly", true),
                Answer("The Good, the Bad, and the Ugly, For a Few Dollars More, A Fistful of Dollars", false),
                Answer("For a Few Dollars More, The Good, the Bad, and the Ugly, A Fistful of Dollars", false),
                Answer("For a Few Dollars More, A Fistful of Dollars, The Good, the Bad, and the Ugly", false)
            )
        ),
        Quiz(
            category = "Science & Nature",
            difficulty = Difficulty.HARD,
            question = "On the Beaufort Scale of wind force, what wind name is given to number 8?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Gale", true),
                Answer("Storm", false),
                Answer("Hurricane", false),
                Answer("Breeze", false)
            )
        ),
        Quiz(
            category = "Entertainment: Cartoon & Animations",
            difficulty = Difficulty.EASY,
            question = "Who is the only voice actor to have a speaking part in all of the Disney Pixar feature films?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("John Ratzenberger", true),
                Answer("Tom Hanks", false),
                Answer("Dave Foley", false),
                Answer("Geoffrey Rush", false)
            )
        ),
        Quiz(
            category = "General Knowledge",
            difficulty = Difficulty.MEDIUM,
            question = "Kraft \"Cheez Whiz\" contains cheese culture, but doesn't actually contain cheese.",
            type = QuizType.BOOLEAN,
            answers = listOf(
                Answer("True", true),
                Answer("False", false)
            )
        ),
        Quiz(
            category = "Entertainment: Video Games",
            difficulty = Difficulty.HARD,
            question = "Danganronpa 2: Goodbye Despair featured all of the surviving students from the first game.",
            type = QuizType.BOOLEAN,
            answers = listOf(
                Answer("False", true),
                Answer("True", false)
            )
        ),
        Quiz(
            category = "History",
            difficulty = Difficulty.MEDIUM,
            question = "Sir Isaac Newton served as a Member of Parliament, but the only recorded time he spoke was to complain about a draft in the chambers.",
            type = QuizType.BOOLEAN,
            answers = listOf(
                Answer("True", true),
                Answer("False", false)
            )
        ),
        Quiz(
            category = "Science: Computers",
            difficulty = Difficulty.HARD,
            question = "America Online (AOL) started out as which of these online service providers?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Quantum Link", true),
                Answer("CompuServe", false),
                Answer("Prodigy", false),
                Answer("GEnie", false)
            )
        ),
        Quiz(
            category = "History",
            difficulty = Difficulty.MEDIUM,
            question = "On which aircraft carrier did the Doolittle Raid launch from on April 18, 1942 during World War II?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("USS Hornet", true),
                Answer("USS Enterprise", false),
                Answer("USS Lexington", false),
                Answer("USS Saratoga", false)
            )
        ),
        Quiz(
            category = "Entertainment: Japanese Anime & Manga",
            difficulty = Difficulty.EASY,
            question = "Which of the following originated as a manga?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Akira", true),
                Answer("Cowboy Bebop", false),
                Answer("High School DxD", false),
                Answer("Gurren Lagann", false)
            )
        ),
        Quiz(
            category = "Entertainment: Comics",
            difficulty = Difficulty.EASY,
            question = "According to their longtime nickname, what kind of \"duo\" are Batman & Robin?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Dynamic", true),
                Answer("Dangerous", false),
                Answer("Dynastic", false),
                Answer("Delirious", false)
            )
        ),
        Quiz(
            category = "History",
            difficulty = Difficulty.EASY,
            question = "The United States of America declared their independence from the British Empire on July 4th, 1776.",
            type = QuizType.BOOLEAN,
            answers = listOf(
                Answer("True", true),
                Answer("False", false)
            )
        ),
        Quiz(
            category = "Geography",
            difficulty = Difficulty.MEDIUM,
            question = "What mountain range lines the border between Spain and France?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Pyrenees", true),
                Answer("Alps", false),
                Answer("Carpathians", false),
                Answer("Urals", false)
            )
        ),
        Quiz(
            category = "General Knowledge",
            difficulty = Difficulty.EASY,
            question = "In a 1994 CBS interview, Microsoft co-founder Bill Gates performed what unusual trick on camera?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Jumping over an office chair", true),
                Answer("Jumping backwards over a desk", false),
                Answer("Standing on his head", false),
                Answer("Typing on a keyboard during a handstand", false)
            )
        ),
        Quiz(
            category = "Science & Nature",
            difficulty = Difficulty.HARD,
            question = "Burning which of these metals will produce a bright white flame?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("Magnesium", true),
                Answer("Copper", false),
                Answer("Lithium", false),
                Answer("Lead", false)
            )
        ),
        Quiz(
            category = "History",
            difficulty = Difficulty.EASY,
            question = "In what year did the Great Northern War, between Russia and Sweden, end?",
            type = QuizType.MULTIPLE_CHOICE,
            answers = listOf(
                Answer("1721", true),
                Answer("1726", false),
                Answer("1727", false),
                Answer("1724", false)
            )
        )
    )

    val categories = listOf(
        Category(9, "General Knowledge"),
        Category(10, "Entertainment: Books"),
        Category(11, "Entertainment: Film"),
        Category(12, "Entertainment: Music"),
        Category(13, "Entertainment: Musicals & Theatres"),
        Category(14, "Entertainment: Television"),
        Category(15, "Entertainment: Video Games"),
        Category(16, "Entertainment: Board Games"),
        Category(17, "Science & Nature"),
        Category(18, "Science: Computers"),
        Category(19, "Science: Mathematics"),
        Category(20, "Mythology"),
        Category(21, "Sports"),
        Category(22, "Geography"),
        Category(23, "History"),
        Category(24, "Politics"),
        Category(25, "Art"),
        Category(26, "Celebrities"),
        Category(27, "Animals"),
        Category(28, "Vehicles"),
        Category(29, "Entertainment: Comics"),
        Category(30, "Science: Gadgets"),
        Category(31, "Entertainment: Japanese Anime & Manga"),
        Category(32, "Entertainment: Cartoon & Animations")
    )

    override fun getTodayQuiz(): Flow<Response<Quiz>> =
        flowOf(Success(quizList.random()))

    override fun getQuizzes(categoryId: Int): Flow<Response<List<Quiz>>> =
        flowOf(Success(quizList))

    override fun getCategories(): Flow<Response<List<Category>>> =
        flowOf(Success(categories))
}