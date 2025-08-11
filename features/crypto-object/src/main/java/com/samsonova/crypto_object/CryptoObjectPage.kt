package com.samsonova.crypto_object

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samsonova.design_system.CryptoPageTemplate

@Composable
fun CryptoObjectPage(modifier: Modifier, cryptoObject: CryptoObjectViewModel = viewModel()) {
    CryptoPageTemplate(
        modifier,
        "CryptoObject",
        "Позволяет использовать биометрию для криптографических операций (шифрование, подпись). " +
                "Ключ хранится в Android Keystore и разблокируется только после аутентификации. " +
                "Без него биометрия только подтверждает личность.",
        { cryptoObject.onEncrypt(it) },
        { cryptoObject.onDecrypt(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun CryptoObjectPagePreview() {
    CryptoObjectPage(modifier = Modifier.padding(20.dp))
}