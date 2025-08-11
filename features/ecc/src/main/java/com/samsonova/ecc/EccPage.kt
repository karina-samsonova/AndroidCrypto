package com.samsonova.ecc

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samsonova.design_system.CryptoPageTemplate

@Composable
fun EccPage(modifier: Modifier, ecc: EccViewModel = viewModel()) {
    CryptoPageTemplate(
        modifier,
        "ECC",
        "Асимметричное шифрование на основе эллиптических кривых. " +
                "Обеспечивает высокую безопасность при меньшем размере ключа, чем RSA. " +
                "Часто используется в мобильных устройствах и блокчейне.",
        { ecc.onEncrypt(it) },
        { ecc.onDecrypt(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun EccPagePreview() {
    EccPage(modifier = Modifier.padding(20.dp))
}