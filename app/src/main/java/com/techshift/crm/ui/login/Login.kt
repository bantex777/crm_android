package com.techshift.crm.ui.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.techshift.crm.R
import com.techshift.crm.data.api.RetrofitClient
import com.techshift.crm.data.repository.AuthRepository
import com.techshift.crm.ui.theme.CRMTheme
import kotlin.text.ifEmpty


@Composable
fun LoginScreen(
    paddingValues: PaddingValues,
    onLoginSuccess: () -> Unit,
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(value = false) }

    var usernameError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Lottie animation code can be added here once raw resource is available
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.log_animation))

    val progress by animateLottieCompositionAsState(
        isPlaying = true,
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 0.7f,
    )

    val repository = remember {
        AuthRepository(RetrofitClient.authApi)
    }


    val factory = remember {
        LoginViewModelFactory(repository)
    }

    val viewModel: LoginViewModel = viewModel(
        factory = factory
    )

    val uiState by viewModel.uiState
        .collectAsStateWithLifecycle()

    LaunchedEffect(uiState.loginResponse) {
        if (uiState.loginResponse != null) {
            onLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LottieAnimation(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterHorizontally),
            composition = composition,
            progress = {progress}
        )


        Text(text = "Login", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = username,
            onValueChange = {username = it},
            label = { Text(usernameError.ifEmpty { "UserName" }, color = if (usernameError.isNotEmpty()) Color.Red else Color.White)},
            leadingIcon = { Icon(Icons.Rounded.AccountCircle, contentDescription = "") },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 20.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = {password = it},
            label = { Text(passwordError.ifEmpty { "Password" }, color = if (passwordError.isNotEmpty()) Color.Red else Color.White)},
            leadingIcon = { Icon(Icons.Rounded.Lock, contentDescription = "") },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.visibility_24px)
                else painterResource(id = R.drawable.visibility_off_24px)

                Icon(
                    painter = image,
                    contentDescription = "",
                    modifier = Modifier.clickable { passwordVisible = !passwordVisible}
                )
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp, horizontal = 20.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
            )
        )


        Spacer(modifier = Modifier.height(24.dp))

        Button (
            onClick = {
                usernameError = if (username.isBlank()) " Username is required" else ""
                passwordError = if (password.isBlank()) "Password is required" else ""
                if (usernameError.isEmpty() && passwordError.isEmpty()) {

                    viewModel.login(username, password)
                    println("Login successful for $username")
                }
            },
            modifier = Modifier.fillMaxWidth().padding(horizontal = 90.dp),
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = "Login")
            }
        }

        if(uiState.error != null) {
            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = uiState.error!!,
                color = Color.Red,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Forgot Password?",
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable {

            }
        )

        Spacer(modifier = Modifier.height(50.dp))

        Row {
            Text(text = "Not a member?")
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Sign in now!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {

                }
            )

        }

    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    CRMTheme {
        LoginScreen(paddingValues = PaddingValues(0.dp)) {}
    }
}
