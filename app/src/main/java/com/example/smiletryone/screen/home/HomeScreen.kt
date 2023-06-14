package com.example.smiletryone.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.smiletryone.component.MyNavDrawerContent
import com.example.smiletryone.component.MyTopAppBar
import kotlinx.coroutines.launch
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.smiletryone.component.TextInput
import com.example.smiletryone.data.remote.responses.DataItem
import com.example.smiletryone.ui.theme.BackGroundMessageBot
import com.example.smiletryone.ui.theme.BackGroundMessageHuman
import com.example.smiletryone.ui.theme.ColorTextBot
import com.example.smiletryone.ui.theme.ColorTextHuman
import com.example.smiletryone.viewmodel.ChatViewModel
import com.example.smiletryone.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel(),
    chatViewModel: ChatViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MyTopAppBar(
                onMenuClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerContent = {
            MyNavDrawerContent(onItemSelected = { title ->
                scope.launch {
                    scaffoldState.drawerState.close()
                }
            }, navController = navController)
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier.fillMaxSize()) {
                MessageList(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp),
                    chatViewModel
                )
                TextInput(chatViewModel)
            }
        }

    }
}

const val ConversationTestTag = "ConversationTestTag"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MessageList(
    modifier: Modifier = Modifier,
    chatViewModel: ChatViewModel
) {
    val scope = rememberCoroutineScope()
    scope.launch {
        chatViewModel.getChat()
    }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val chatItems by chatViewModel.chatItems.collectAsState()
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding =
            WindowInsets.statusBars.add(WindowInsets(top = 90.dp)).asPaddingValues(),
            modifier = Modifier
                .testTag(ConversationTestTag)
                .fillMaxSize(),
            reverseLayout = false,
            state = listState,
        ) {
            coroutineScope.launch {
                listState.animateScrollToItem(chatItems.size )
            }
            items(chatItems) { chatItem ->
                MessageCard(message = chatItem, isHuman = true)
                MessageCard(message = chatItem)
            }
        }
    }

}

@Composable
fun MessageCard(message: DataItem, isHuman: Boolean = false, isLast: Boolean = false) {
    Column(
        horizontalAlignment = if (isHuman) Alignment.End else Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .padding(top = if (isLast) 120.dp else 0.dp)
    ) {
        Box(
            modifier = Modifier
                .widthIn(0.dp, 260.dp) //mention max width here
                .background(
                    if (isHuman) BackGroundMessageHuman else BackGroundMessageBot,
                    shape = RoundedCornerShape(12.dp)
                ),
        ) {
            if (isHuman) {
                HumanMessageCard(message = message)
            } else {
                BotMessageCard(message = message)
            }
        }
    }
}

@Composable
fun HumanMessageCard(message: DataItem) {
    Text(
        text = message.question,
        fontSize = 14.sp,
        color = ColorTextBot,
        modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
        textAlign = TextAlign.Justify,
    )
}

@Composable
fun BotMessageCard(message: DataItem) {
    Text(
        text = message.reply,
        fontSize = 14.sp,
        color = ColorTextBot,
        modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp),
        textAlign = TextAlign.Justify,
    )
}