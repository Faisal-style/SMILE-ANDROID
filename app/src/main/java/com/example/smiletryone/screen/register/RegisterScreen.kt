package com.example.smiletryone.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.smiletryone.R
import com.example.smiletryone.component.CustomButton
import com.example.smiletryone.component.GifSmile
import com.example.smiletryone.component.TextFieldComponent
import com.example.smiletryone.component.TextFieldPasswordComponent
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.screen.login.LoginTitle
import com.example.smiletryone.screen.login.SignUp
import com.example.smiletryone.ui.theme.PurplePurse
import com.example.smiletryone.util.Resource
import com.example.smiletryone.viewmodel.RegisterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    registerViewModel: RegisterViewModel = hiltViewModel()
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
    val isLoading by remember { registerViewModel.isLoading }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("Email atau Password Salah") }

    Box(modifier = modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { paddingValues ->
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .paint(
                            painter = painterResource(id = R.drawable.img),
                            contentScale = ContentScale.FillBounds
                        )
                        .padding(paddingValues)
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
                        Column(
                            modifier = modifier,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {

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
                                onValueChange = { newPasswordValue ->
                                    passwordTextField = newPasswordValue
                                },
                                placeholder = "Password"
                            )

                            Spacer(modifier = Modifier.size(8.dp))

                            TextFieldPasswordComponent(
                                state = passwordTextField2,
                                onValueChange = { newPasswordValue ->
                                    passwordTextField2 = newPasswordValue
                                },
                                placeholder = "Password"
                            )

                            Spacer(modifier = Modifier.size(8.dp))

                            CustomButton(text = "Register") {
                                registerViewModel.viewModelScope.launch {
                                    if (passwordTextField != passwordTextField2) {
                                        snackbarMessage = "Password Tidak Sama"
                                        snackbarVisible = true
                                        delay(1000)
                                        snackbarVisible = false
                                    } else {
                                        val getRegister = registerViewModel.getRegisterInfo(
                                            emailTextField,
                                            passwordTextField,
                                            usernameTextField
                                        )
                                        if (getRegister is Resource.Success) {
                                            snackbarMessage = "Register Berhasil"
                                            snackbarVisible = true
                                            delay(1000)
                                            snackbarVisible = false
                                            navController.navigate(Screen.Login.route)
                                        } else {
                                            snackbarMessage = "Register Gagal"
                                            snackbarVisible = true
                                            delay(1000)
                                            snackbarVisible = false
                                        }
                                    }
                                }

                            }
                        }
                        Spacer(modifier = Modifier.size(10.dp))
                        SignIn() {
                            navController.navigate(Screen.Login.route)
                        }
                    }
                }
            },
            snackbarHost = {
                if (snackbarVisible) {
                    Snackbar(
                        modifier = Modifier.padding(8.dp),
                        action = {
                            TextButton(onClick = { snackbarVisible = false }) {
                                Text(text = "OK")
                            }
                        }
                    ) {
                        Text(text = snackbarMessage)
                    }
                }
            }
        )
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
            painter = painterResource(id = R.drawable.logo_only),
            contentDescription = "Register Image",
            modifier = Modifier
                .width(180.dp)
                .height(153.dp)
        )
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