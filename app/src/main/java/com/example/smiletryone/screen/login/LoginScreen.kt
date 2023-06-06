package com.example.smiletryone.screen.login

import android.graphics.fonts.FontStyle
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FabPosition
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.smiletryone.R
import com.example.smiletryone.component.CustomButton
import com.example.smiletryone.component.TextFieldComponent
import com.example.smiletryone.component.TextFieldPasswordComponent
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.ui.theme.PurplePurse

@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.img),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LoginTitle()
            Spacer(modifier = Modifier.size(30.dp))
            InputField()
            Spacer(modifier = Modifier.size(10.dp))
            SignUp(){
                navController.navigate(Screen.Register.route)
            }
        }
    }
}


@Composable
fun LoginTitle(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "S.M.I.L.E",
            fontFamily = PurplePurse,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
        Image(
            painter = painterResource(id = R.drawable.login_image),
            contentDescription = "Login Image",
            modifier = Modifier
                .width(180.dp)
                .height(153.dp)
        )
    }
}

@Composable
fun InputField(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var emailTextField by rememberSaveable {
            mutableStateOf("")
        }
        var passwordTextField by rememberSaveable {
            mutableStateOf("")
        }
        TextFieldComponent(
            state = emailTextField,
            placeholder = "Email",
            onValueChange = { newValue -> emailTextField = newValue })
        Spacer(modifier = Modifier.size(8.dp))
        TextFieldPasswordComponent(
            state = passwordTextField,
            onValueChange = { newPasswordValue -> passwordTextField = newPasswordValue },
            placeholder = "Password"
        )
        Spacer(modifier = Modifier.size(8.dp))
        CustomButton(text = "Login") {
            println("Login Text $emailTextField")
        }
    }
}

@Composable
fun SignUp(modifier: Modifier = Modifier, navigateToRegister: ()-> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Doesn’t have an account ?")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Sign up Here", modifier = Modifier.clickable { navigateToRegister() })
    }
}