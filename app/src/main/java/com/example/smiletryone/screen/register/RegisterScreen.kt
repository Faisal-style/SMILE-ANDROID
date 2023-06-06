package com.example.smiletryone.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import com.example.smiletryone.screen.login.LoginTitle
import com.example.smiletryone.screen.login.SignUp
import com.example.smiletryone.ui.theme.PurplePurse

@Composable
fun RegisterScreen(navController: NavHostController, modifier: Modifier = Modifier) {
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
            RegisterTitle()
            Spacer(modifier = Modifier.size(30.dp))
            InputField()
            Spacer(modifier = Modifier.size(10.dp))
            SignIn(){
                navController.navigate(Screen.Login.route)
            }
        }
    }
}

@Composable
fun RegisterTitle(modifier: Modifier = Modifier) {
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
            contentDescription = "Register Image",
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
        var usernameTextField by rememberSaveable {
            mutableStateOf("")
        }
        var passwordTextField by rememberSaveable {
            mutableStateOf("")
        }
        var passwordTextField2 by rememberSaveable {
            mutableStateOf("")
        }
        TextFieldComponent(
            state = emailTextField,
            placeholder = "Email",
            onValueChange = { newValue -> emailTextField = newValue })

        Spacer(modifier = Modifier.size(8.dp))

        TextFieldComponent(
            state = usernameTextField,
            placeholder = "Username",
            onValueChange = { newValue -> usernameTextField = newValue })

        Spacer(modifier = Modifier.size(8.dp))

        TextFieldPasswordComponent(
            state = passwordTextField,
            onValueChange = { newPasswordValue -> passwordTextField = newPasswordValue },
            placeholder = "Password"
        )

        Spacer(modifier = Modifier.size(8.dp))

        TextFieldPasswordComponent(
            state = passwordTextField2,
            onValueChange = { newPasswordValue -> passwordTextField2 = newPasswordValue },
            placeholder = "Password"
        )

        Spacer(modifier = Modifier.size(8.dp))

        CustomButton(text = "Register") {
            println("Login Text $emailTextField")
        }
    }
}

@Composable
fun SignIn(modifier: Modifier = Modifier, navigateToLogin: () -> Unit) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Have an Account ?")
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Sign in Here", modifier = Modifier.clickable { navigateToLogin() })
    }
}