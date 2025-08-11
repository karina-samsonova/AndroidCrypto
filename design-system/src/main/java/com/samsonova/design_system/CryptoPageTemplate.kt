package com.samsonova.design_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun CryptoPageTemplate(
    modifier: Modifier,
    header: String,
    description: String,
    onEncrypt: (String) -> String,
    onDecrypt: (String) -> String,
) {
    var secretMessage by rememberSaveable { mutableStateOf("") }
    var encryptedMessage by rememberSaveable { mutableStateOf("") }
    var decryptedMessage by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = header,
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily(Font(R.font.press_start))
        )
        Spacer(modifier = Modifier.height(24.dp))
        TypeWriterText(
            text = description,
            style = TextStyle.Default.copy(
                color = Color.White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.jura))
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        )
        CyberTextField(
            value = secretMessage,
            onValueChange = { secretMessage = it },
            label = {
                Text(
                    text = "Enter your secret message",
                    fontFamily = FontFamily(Font(R.font.jura))
                )
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                colors = cyberButtonColors,
                onClick = {
                    encryptedMessage = onEncrypt(secretMessage)
                },
                enabled = secretMessage.isNotEmpty()
            ) {
                Text(text = "Encrypt", fontFamily = FontFamily(Font(R.font.jura)))
            }
            Spacer(modifier = Modifier.width(8.dp))
            CyberTextField(
                value = encryptedMessage,
                onValueChange = { encryptedMessage = it },
                label = { },
                readOnly = true
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                colors = cyberButtonColors,
                onClick = {
                    decryptedMessage = onDecrypt(encryptedMessage)
                },
                enabled = encryptedMessage.isNotEmpty()
            ) {
                Text(text = "Decrypt", fontFamily = FontFamily(Font(R.font.jura)))
            }
            Spacer(modifier = Modifier.width(8.dp))
            CyberTextField(
                value = decryptedMessage,
                onValueChange = { decryptedMessage = it },
                label = { },
                readOnly = true
            )
        }
    }
}

@Composable
fun CyberTextField(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    value: String,
    label: @Composable() (() -> Unit)? = null,
    readOnly: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        readOnly = readOnly,
        modifier = modifier,
        maxLines = 2,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Teal,
            unfocusedTextColor = Color.White,
            unfocusedBorderColor = Color.White,
            focusedTextColor = Color.White,
            disabledTextColor = Color.White,
            focusedLabelColor = Teal,
            unfocusedLabelColor = Color.White,
            errorTextColor = Color(0xFFCD7DFF),
            errorSupportingTextColor = Color(0xFFCD7DFF)
        )
    )
}

@Composable
fun TypeWriterText(
    text: String,
    modifier: Modifier = Modifier,
    delayBetweenWords: Duration = 100.milliseconds,
    delayBetweenChars: Duration = 50.milliseconds,
    style: TextStyle = TextStyle.Default
) {
    var currentText by remember { mutableStateOf("") }

    LaunchedEffect(text) {
        currentText = ""
        text.split(" ").forEachIndexed { wordIndex, word ->
            if (wordIndex > 0) {
                delay(delayBetweenWords)
                currentText += " "
            }

            word.forEachIndexed { charIndex, char ->
                delay(delayBetweenChars)
                currentText += char
            }
        }
    }

    Text(
        text = currentText,
        modifier = modifier,
        style = style
    )
}