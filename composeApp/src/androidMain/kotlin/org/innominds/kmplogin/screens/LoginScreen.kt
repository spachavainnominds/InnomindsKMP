package org.innominds.kmplogin.screens

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.innominds.kmplogin.R
import org.innominds.kmplogin.models.Credentials

@Composable
fun LoginScreen() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Content()
    }
}

@Composable
fun Content() {
    val credentials by remember { mutableStateOf(Credentials()) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        TitleText()
        Spacer(modifier = Modifier.padding(10.dp))
        UserNameField(onChange = { })
        Spacer(modifier = Modifier.padding(5.dp))
        PasswordField(onChange = { })
        Spacer(modifier = Modifier.padding(10.dp))
        LoginButton(credentials.login, credentials.pwd)
    }
}

@Composable
fun TitleText() {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Login",
        color = Color(0xFF01579B),
        style = androidx.compose.ui.text.TextStyle(
            fontSize = MaterialTheme.typography.h3.fontSize,
        ),
        textAlign = androidx.compose.ui.text.style.TextAlign.Left,
    )
}

@Composable
fun UserNameField( onChange: (String) -> Unit,) {
    var userName by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        value = userName,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            keyboardType = KeyboardType.Password
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_username),
                contentDescription = "User Name"
            )
        },
        label = { Text(text = "Enter User Name") },
        onValueChange = onChange,
    )
}

@Composable
fun PasswordField(onChange: (String) -> Unit) {
    val pasword by remember { mutableStateOf(TextFieldValue("")) }

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_password),
                contentDescription = "User Name"
            )
        },

        value = pasword.text,
        label = { Text(text = "Enter Password") },
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password, ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                painterResource(R.drawable.ic_visibility)
            else painterResource(R.drawable.ic_visibility_off)

            // Please provide localized description for accessibility services
            val description = if (passwordVisible) "Hide password" else "Show password"

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(painter = image, description)
            }
        }
    )
}

@Composable
fun LoginButton(userName: String, password: String) {
    val context = LocalContext.current
    println("User Name: $userName, Password: $password")
    Button(
        onClick = {
            println("User Name: $userName, Password: $password")
          /*  Toast.makeText(
                context,
                "User Name: $userName, Password: $password",
                Toast.LENGTH_SHORT
            ).show()*/
        },
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .height(45.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF01579B))
    ) {
        Text("Login", fontSize = 20.sp, color = Color.White)
    }
}

@Preview(
    showSystemUi = true, showBackground = true,
    uiMode = UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun LoginScreenPreview() {
    Content()
}