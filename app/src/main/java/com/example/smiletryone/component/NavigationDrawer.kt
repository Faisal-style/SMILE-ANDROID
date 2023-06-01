package com.example.smiletryone.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.smiletryone.R
import com.example.smiletryone.ui.theme.Monda_Bold
import com.example.smiletryone.ui.theme.Monda_Regular
import com.example.smiletryone.ui.theme.PurplePurse

@Composable
fun MyTopAppBar(onMenuClick: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = stringResource(id = R.string.menu)
                )
            }
        },
        title = {
            Text(text = stringResource(id = R.string.app_name), fontFamily = PurplePurse)
        }
    )
}

@Composable
fun MyNavDrawerContent(
    modifier: Modifier = Modifier,
    onItemSelected: (title: String) -> Unit
) {
    val items = listOf(
        MenuItem(
            title = stringResource(id = R.string.home),
            icon = Icons.Default.Home
        ),
        MenuItem(
            title = stringResource(id = R.string.history),
            icon = ImageVector.vectorResource(id = R.drawable.baseline_history_24)
        ),
        MenuItem(
            title = stringResource(id = R.string.logout),
            icon = ImageVector.vectorResource(id = R.drawable.baseline_logout_24)
        ),
    )
    Column(
        modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(220.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.primary)
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp),
                horizontalAlignment = Alignment.Start
            ) {
                AsyncImage(
                    model = "https://img.freepik.com/free-vector/businessman-character-avatar-isolated_24877-60111.jpg?w=2000",
                    contentDescription = "Avtar Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(CircleShape)

                )
                Text(
                    text = "Zidan Noor Irfan Kontol",
                    fontSize = 20.sp,
                    color = Color.White,
                    fontFamily = Monda_Bold,
                    modifier = Modifier
                        .padding(top = 8.dp)
                )
                Text(
                    text = "zidan@gmail.com",
                    fontSize = 15.sp,
                    color = Color.White,
                    fontFamily = Monda_Regular,
                )
            }

        }
        for (item in items) {
            Row(
                modifier = Modifier
                    .clickable { onItemSelected(item.title) }
                    .padding(vertical = 12.dp, horizontal = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = item.icon, contentDescription = item.title, tint = Color.Black)
                Spacer(modifier = Modifier.width(32.dp))
                Text(text = item.title, fontFamily = Monda_Regular)
            }
        }
        Divider()

    }
}
