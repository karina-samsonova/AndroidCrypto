package com.samsonova.rsa

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samsonova.design_system.CryptoPageTemplate

@Composable
fun RsaPage(modifier: Modifier, rsa: RsaViewModel = viewModel()) {
    CryptoPageTemplate(
        modifier,
        "RSA",
        "Асимметричное шифрование с парой ключей (публичный и приватный). " +
                "Идеально для безопасной передачи данных, но медленнее симметричных методов. " +
                "Шифрует небольшие объемы данных (~245 байт для 2048-битного ключа).",
        { rsa.onEncrypt(it) },
        { rsa.onDecrypt(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun RsaPagePreview() {
    RsaPage(modifier = Modifier.padding(20.dp))
}