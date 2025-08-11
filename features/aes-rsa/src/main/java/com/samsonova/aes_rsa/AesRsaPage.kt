package com.samsonova.aes_rsa

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samsonova.design_system.CryptoPageTemplate

@Composable
fun AesRsaPage(modifier: Modifier, rsa: AesRsaViewModel = viewModel()) {
    CryptoPageTemplate(
        modifier,
        "AES-RSA",
        "Комбинация AES (для данных) и RSA (для ключа). " +
                "Быстрое шифрование больших данных AES + безопасная передача ключа через RSA. " +
                "Оптимально для защищенной передачи файлов.",
        { rsa.onEncrypt(it) },
        { rsa.onDecrypt(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun AesRsaPagePreview() {
    AesRsaPage(modifier = Modifier.padding(20.dp))
}