package com.example.smiletryone.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.smiletryone.R

@Composable
fun TextFieldComponent(state: String, onValueChange: (String) -> Unit, placeholder: String) {
    OutlinedTextField(
        value = state,
        onValueChange = { textFieldValue -> onValueChange(textFieldValue) },
        placeholder = { Text(placeholder) },
        colors = TextFieldDefaults
            .textFieldColors(
                backgroundColor = colorResource(id = R.color.textFieldColor),
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                disabledTextColor = Color.Transparent,
            ),
        shape = RoundedCornerShape(20.dp),
        maxLines = 1,
    )

}