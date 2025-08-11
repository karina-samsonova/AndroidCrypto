package com.samsonova.aes

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samsonova.design_system.CryptoPageTemplate

@Composable
fun AesPage(modifier: Modifier, aes: AesViewModel = viewModel()) {
    CryptoPageTemplate(
        modifier,
        "AES",
        "Симметричное шифрование основано на стандарте Advanced Encryption Standard (AES). " +
                "Быстрое и эффективное для больших данных. " +
                "Использует один ключ для шифрования и дешифрования.",
        { aes.onEncrypt(it) },
        { aes.onDecrypt(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun AesPagePreview() {
    AesPage(modifier = Modifier.padding(20.dp))
}