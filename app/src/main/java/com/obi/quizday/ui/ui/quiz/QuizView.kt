package com.obi.quizday.ui.ui.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obi.quizday.ui.data.quizzes.QuizRepositoryMockImpl
import com.obi.quizday.ui.domain.quizzez.model.Answer
import com.obi.quizday.ui.domain.quizzez.model.Quiz

@Composable
fun QuizView(
    modifier: Modifier = Modifier,
    quiz: Quiz,
    questionNumber: Int,
    totalQuestionsNumber: Int,
    listener: QuizScreenClickListener
) {
    var selectedAnswer: Answer? by remember { mutableStateOf(null) }

    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            text = buildAnnotatedString {
                val numberStyle = SpanStyle(
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                val outOfStyle = SpanStyle(
                    fontSize = 17.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                withStyle(numberStyle) {
                    append("Question $questionNumber/")
                }
                withStyle(outOfStyle) {
                    append(totalQuestionsNumber.toString())
                }
            }
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.4f)
                    .wrapContentHeight(Alignment.CenterVertically),
                text = AnnotatedString.fromHtml(quiz.question.orEmpty()),
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                textAlign = TextAlign.Center

            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                quiz.answers.forEach { answer ->
                    val color = if (answer == selectedAnswer) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        Color.Transparent
                    }
                    val textColor = if (answer == selectedAnswer) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurface
                    }
                    Text(
                        modifier = Modifier
                            .clickable {
                                selectedAnswer = answer
                            }
                            .fillMaxWidth()
                            .background(
                                color,
                                RoundedCornerShape(20.dp)
                            )
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.primary,
                                RoundedCornerShape(20.dp)
                            )
                            .padding(16.dp),
                        text = AnnotatedString.fromHtml(answer.text),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        color = textColor
                    )
                }
            }
            Button(
                modifier = Modifier.padding(top = 24.dp),
                shape = RoundedCornerShape(20.dp),
                enabled = selectedAnswer != null,
                onClick = {
                    selectedAnswer?.let { listener.onAnswerSelected(it) }
                }
            ) {
                Text(
                    modifier=Modifier.padding(12.dp),
                    text = "Next",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
        }
    }


}

@Preview(showBackground = true)
@Composable
private fun QuizPreview() {
    QuizView(
        quiz = QuizRepositoryMockImpl.quiz,
        listener = QuizScreenClickListener.mocked,
        questionNumber = 3,
        totalQuestionsNumber = 10
    )
}
