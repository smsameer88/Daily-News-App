package com.sameer.dailypulse.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.sameer.dailypulse.articles.application.Article
import com.sameer.dailypulse.articles.presentation.ArticlesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
    onAboutButtonClick: () -> Unit,
    onListButtonClick: () -> Unit,
    articlesViewModel: ArticlesViewModel = getViewModel(),
) {
    val articlesState = articlesViewModel.articleState.collectAsState()

    Column {
        AppBar(onAboutButtonClick, onListButtonClick)

        if (articlesState.value.error != null)
            ErrorMessage(articlesState.value.error!!)
        if (articlesState.value.articles.isNotEmpty())
            ArticlesListView(articlesViewModel)
    }
}

@Composable
fun ArticlesListView(viewModel: ArticlesViewModel) {

    SwipeRefresh(
        state = SwipeRefreshState(viewModel.articleState.value.loading),
        onRefresh = { viewModel.getArticles(forceFetch = true) },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.articleState.value.articles) { article ->
                ArticleItemView(article)
            }
        }
    }

}

@Composable
fun ArticleItemView(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 22.sp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = article.desc)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = article.date,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun ErrorMessage(message: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = TextStyle(
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    onAboutButtonClick: () -> Unit,
    onListButtonClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = "Articles") },
        actions = {
            Row {
                IconButton(onClick = onListButtonClick) {
                    Icon(
                        imageVector = Icons.Outlined.List,
                        contentDescription = "List Button"
                    )
                }
                IconButton(onClick = onAboutButtonClick) {
                    Icon(
                        imageVector = Icons.Outlined.Info,
                        contentDescription = "About Device Button"
                    )
                }
            }
        },
    )
}
