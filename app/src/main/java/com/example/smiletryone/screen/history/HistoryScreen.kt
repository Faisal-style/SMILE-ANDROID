package com.example.smiletryone.screen.history

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.stringResource
import com.example.smiletryone.R
import com.example.smiletryone.ui.theme.PurplePurse
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.smiletryone.component.GifSmile
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.viewmodel.ChatViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HistoryScreen(navController: NavController, chatViewModel: ChatViewModel = hiltViewModel()) {
    BackHandler() {
        navController.navigate(Screen.Home.route)
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        scope.launch {
            chatViewModel.isLoading.value = true
            chatViewModel.getConversation()
            delay(1000)
            chatViewModel.isLoading.value = false
        }
    }
    val conversationItems by chatViewModel.conversationItems.collectAsState()
    val isLoading by remember { chatViewModel.isLoading }
    Box() {
        Scaffold(
            topBar = {
                TopAppBar(
                    onBackClick = {
                        navController.popBackStack()
                        navController.clearBackStack(Screen.Home.route)
                    }
                )
            },
            floatingActionButton = {
                ExtendedFloatingActionButton(
                    text = { Text(text = "Add Chat") },
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.baseline_add_comment_24),
                            contentDescription = "add chat button"
                        )
                    },
                    onClick = {
                        scope.launch {
                            chatViewModel.newConversation()
                            delay(2000)
                            navController.popBackStack()
                            navController.navigate(Screen.Home.route)
                        }

                    })
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {

                LazyColumn(
                    Modifier.fillMaxWidth()
                ) {
                    items(conversationItems) { conversationItem ->
                        ConversationItem(text = conversationItem.title,
                            onConversationClicked = {
                                chatViewModel.saveConversationId(conversationItem.id)
                                navController.popBackStack()
                                navController.navigate(Screen.Home.route)
                            },
                            onDeleteClicked = {
                                chatViewModel.deleteConversation(conversationItem.id)
                                navController.navigate(Screen.History.route)
                            })
                    }
                }
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .wrapContentSize(Alignment.Center)
            ) {
                GifSmile(modifier = Modifier.size(100.dp))
            }
        }
    }
}


@Composable
fun TopAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        },
        title = {
            Text(
                text = stringResource(id = R.string.history_screen),
                fontFamily = PurplePurse
            )
        }
    )
}

@Composable
private fun ConversationItem(
    text: String,
    icon: ImageVector = Icons.Filled.Edit,
    onConversationClicked: () -> Unit,
    onDeleteClicked: () -> Unit
) {
    Row() {
        Row(
            modifier = Modifier
                .height(56.dp)
                .weight(1f)
                .padding(end = 4.dp, start = 12.dp, top = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = colorResource(id = R.color.splash_color))
                .clickable(onClick = onConversationClicked),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                tint = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .size(25.dp),
                contentDescription = null,
            )
            Text(
                text = text,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(start = 12.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Icon(
            ImageVector.vectorResource(id = R.drawable.baseline_delete_forever_24),
            tint = Color.Red,
            modifier = Modifier
                .padding(top = 16.dp, bottom = 16.dp, end = 8.dp)
                .size(35.dp)
                .clickable(onClick = onDeleteClicked),
            contentDescription = null,
        )
    }
}