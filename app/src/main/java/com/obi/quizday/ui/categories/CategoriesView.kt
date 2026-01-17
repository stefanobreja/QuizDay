package com.obi.quizday.ui.categories

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.obi.quizday.data.quizzes.QuizRepositoryMockImpl
import com.obi.quizday.domain.quizzez.model.Category
import com.obi.quizday.ui.theme.LocalCustomColorsPalette

@Composable
fun CategoriesView(
    modifier: Modifier = Modifier,
    categories: List<Category>,
    onCategorySelected: (Int?) -> Unit
) {
    val customColors = LocalCustomColorsPalette.current
    val colors = remember {
        listOf(
            customColors.pastelBlue,
            customColors.pastelGreen,
            customColors.pastelYellow,
            customColors.pastelOrange,
            customColors.pastelPink,
            customColors.pastelPurple
        )
    }

    Column(modifier = modifier.padding(20.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            text = "Please select a category to get started"
        )

        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            columns = GridCells.Fixed(1),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(categories.size) { index ->
                val color = colors[index % colors.size]
                CategoryView(
                    item = categories[index],
                    color = color,
                    onCategorySelected = onCategorySelected
                )
            }
        }
    }
}

@Composable
fun CategoryView(
    modifier: Modifier = Modifier,
    item: Category,
    color: Color,
    onCategorySelected: (Int?) -> Unit
) {
    Card(
        modifier = modifier
            .clickable {
                onCategorySelected(item.id)
            }
//            .aspectRatio(1f)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 24.dp)
                .fillMaxSize()
                .wrapContentHeight(Alignment.CenterVertically),
            text = item.name ?: "?",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.inverseOnSurface
            else MaterialTheme.colorScheme.onSurface
        )

    }
}

@Preview
@Composable
private fun CategoriesPreview() {
    CategoriesView(
        categories = QuizRepositoryMockImpl.categories,
        onCategorySelected = { }
    )
}
