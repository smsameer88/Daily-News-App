package com.sameer.dailypulse.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.sameer.dailypulse.sources.application.Source
import com.sameer.dailypulse.sources.presentation.SourcesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun SourcesScreen(
    sourcesViewModel: SourcesViewModel = getViewModel(),
    onUpButtonClick: () -> Unit,
) {
    val articlesState = sourcesViewModel.sourceState.collectAsState()

    Column {
        Toolbar(title = "Sources", onUpButtonClick)

        if (articlesState.value.error != null)
            ErrorMessage(articlesState.value.error!!)
        if (articlesState.value.sources.isNotEmpty())
            ArticlesListView(sourcesViewModel)
    }
}

@Composable
fun ArticlesListView(viewModel: SourcesViewModel) {

    SwipeRefresh(
        state = SwipeRefreshState(viewModel.sourceState.value.loading),
        onRefresh = { viewModel.getSources() },
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(viewModel.sourceState.value.sources) { article ->
                ArticleItemView(article)
            }
        }
    }

}

@Composable
fun ArticleItemView(article: Source) {
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
            text = article.origin,
            style = TextStyle(color = Color.Gray),
            modifier = Modifier.align(Alignment.End)
        )
        Spacer(modifier = Modifier.height(4.dp))
    }
}