package com.samsonova.rsa

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator
import javax.crypto.Cipher
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class RsaViewModel : ViewModel() {
    private var keyPair: KeyPair? = null

    init {
        try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            keyPair = keyPairGenerator.generateKeyPair()
        } catch (e: Exception) {
            Log.e("Crypto", "Key pair error")
        }
    }

    @SuppressLint("GetInstance")
    fun makeRsa(rawMessage: ByteArray?, cipherMode: Int, key: Key?): ByteArray {
        try {
            val cipher = Cipher.getInstance("RSA")
            cipher.init(cipherMode, key)
            val output = cipher.doFinal(rawMessage)
            return output
        } catch (e: Exception) {
            e.printStackTrace()
            return byteArrayOf()
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun onEncrypt(targetString: String): String {
        val encryptedBytes: ByteArray =
            makeRsa(targetString.toByteArray(), Cipher.ENCRYPT_MODE, keyPair?.public)
        return Base64.encode(encryptedBytes)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun onDecrypt(encryptedString: String): String {
        val encryptedBytes = Base64.decode(encryptedString)
        val decryptedString: ByteArray = makeRsa(encryptedBytes, Cipher.DECRYPT_MODE, keyPair?.private)
        return String(decryptedString)
    }
}