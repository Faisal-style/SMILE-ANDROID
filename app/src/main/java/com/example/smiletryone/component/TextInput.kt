package com.example.smiletryone.component

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smiletryone.viewmodel.ChatViewModel

@Composable
fun TextInput(
    viewModel: ChatViewModel
) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    val isError by remember { viewModel.isError }
    Box(
        modifier = Modifier
            .navigationBarsPadding()
            .imePadding()

    ) {
        Column {
            Divider(Modifier.height(0.2.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .padding(top = 6.dp, bottom = 10.dp)
                    .border(shape = RoundedCornerShape(10.dp), color = Color.Gray, width = 2.dp),

                ) {
                Row {
                    TextField(
                        value = text,
                        onValueChange = {
                            text = it
                        },
                        label = null,
                        placeholder = { Text("Ask me anything", fontSize = 12.sp) },
                        shape = RoundedCornerShape(25.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                            .weight(1f),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent,
                        ),
                    )
                    IconButton(onClick = {
                        viewModel.sendChat(text.text)
                        text = TextFieldValue("")

                    }) {
                        Icon(
                            Icons.Filled.Send,
                            "sendMessage",
                            modifier = Modifier.size(26.dp),
                        )
                    }
                }
            }
        }
        if(isError){
            Toast.makeText(context, "Request Timeout, Periksa Koneksi Anda", Toast.LENGTH_SHORT).show()
            viewModel.isError.value = false
        }
    }
}