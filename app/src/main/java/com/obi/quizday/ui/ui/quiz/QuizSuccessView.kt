package com.obi.quizday.ui.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obi.quizday.ui.data.Response
import com.obi.quizday.ui.data.quizzes.QuizRepositoryMockImpl
import com.obi.quizday.ui.domain.Quiz
import com.obi.quizday.ui.ui.theme.PurpleBackground

@Composable
fun QuizView(
    modifier: Modifier = Modifier,
    response: Response<Quiz>
) {
    Column(
        modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        when (response) {
            is Response.Error -> {}
            Response.Limbo -> {}
            Response.Loading -> {}
            is Response.Success<Quiz> -> response.data?.let {
                QuizSuccessView(quiz = response.data)
            }
        }

    }
}

@Composable
private fun QuizSuccessView(
    modifier: Modifier = Modifier,
    quiz: Quiz
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                text = AnnotatedString.fromHtml(quiz.question.orEmpty()),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp, textAlign = TextAlign.Center
            )
        }
        Spacer(Modifier.padding(16.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            quiz.answers.forEach { answer ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(PurpleBackground, RoundedCornerShape(8.dp))
                        .padding(16.dp),
                    text = AnnotatedString.fromHtml(answer),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.inverseOnSurface
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun QuizPreview() {
    QuizView(
        response = Response.Success(QuizRepositoryMockImpl.quiz)
    )
}
