package com.example.smiletryone.screen.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.smiletryone.R
import com.example.smiletryone.component.CustomButton
import com.example.smiletryone.component.TextFieldComponent
import com.example.smiletryone.component.TextFieldPasswordComponent
import com.example.smiletryone.navigation.Screen
import com.example.smiletryone.ui.theme.PurplePurse
import com.example.smiletryone.viewmodel.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    var emailTextField by rememberSaveable {
        mutableStateOf("")
    }
    var passwordTextField by rememberSaveable {
        mutableStateOf("")
    }
    val isLoading by remember { loginViewModel.isLoading }
    var snackbarVisible by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("Email atau Password Salah") }

    val token = loginViewModel.token.value
    val homeDestination = loginViewModel.homeDestination.value

    LaunchedEffect(key1 = token) {
        if (token.isNotBlank()) {
            navController.navigate(homeDestination)
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        content = { paddingValues ->
            Box(
                modifier = Modifier
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
                    LoginTitle()
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
                        TextFieldPasswordComponent(
                            state = passwordTextField,
                            onValueChange = { newPasswordValue ->
                                passwordTextField = newPasswordValue
                            },
                            placeholder = "Password"
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        CustomButton(text = "Login") {
                            loginViewModel.viewModelScope.launch {
                                loginViewModel.getLoginInfo(emailTextField, passwordTextField)
                                val userToken =
                                    loginViewModel.loginData.value?.loginResult?.accessToken
                                val userId = loginViewModel.loginData.value?.loginResult?.id
                                println(userId)
                                if (userToken != null && userId != null) {
                                    loginViewModel.saveUserToken(userToken)
                                    loginViewModel.saveUserId(userId)
                                } else {
                                    snackbarVisible = true
                                    snackbarMessage = "Email atau password Salah"
                                    delay(1000)
                                    snackbarVisible = false
                                }

                            }
                        }
                        if (isLoading) {
                            CircularProgressIndicator(color = MaterialTheme.colors.primary)
                        }

                    }

                    Spacer(modifier = Modifier.size(10.dp))
                    SignUp() {
                        navController.navigate(Screen.Register.route)
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
fun SignUp(modifier: Modifier = Modifier, navigateToRegister: () -> Unit) {
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

