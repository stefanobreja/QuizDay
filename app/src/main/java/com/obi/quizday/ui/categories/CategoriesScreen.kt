package com.obi.quizday.ui.categories

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.obi.quizday.data.Response
import com.obi.quizday.domain.quizzez.model.Category
import com.obi.quizday.ui.common.view.GenericErrorView
import com.obi.quizday.ui.navigation.Route

@Composable
fun CategoriesScreen(
    modifier: Modifier = Modifier,
    viewModel: CategoriesViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val context = LocalContext.current
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    when (categories) {
        is Response.Success<List<Category>> -> (categories as Response.Success<List<Category>>).data?.let {
            CategoriesView(
                modifier = modifier,
                categories = it,
                onCategorySelected = { id ->
                    if (id == null) {
                        Toast.makeText(context, "We encountered an issue, please try again later", Toast.LENGTH_LONG)
                            .show()
                    } else {
                        navController.navigate(Route.Quizzes(id))
                    }
                }
            )
        }

        Response.Loading -> Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp)
            )
        }

        is Response.Error -> {
            GenericErrorView(modifier)
        }


        else -> {
            // no-op
        }
    }
}