package com.samsonova.crypto_object

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import java.security.Key
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class CryptoObjectViewModel(application: Application) : AndroidViewModel(application) {
    private var secretKey: SecretKey? = null

    init {
        try {
            val keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(256)

            secretKey = keyGenerator.generateKey()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("GetInstance")
    fun makeAes(rawMessage: ByteArray?, cipherMode: Int): ByteArray {
        try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(cipherMode, this.secretKey)
            val output = cipher.doFinal(rawMessage)
            return output
        } catch (e: Exception) {
            e.printStackTrace()
            return byteArrayOf()
        }
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun onEncrypt(targetString: String): String {
        val encryptedBytes: ByteArray = makeAes(targetString.toByteArray(), Cipher.ENCRYPT_MODE)
        return Base64.encode(encryptedBytes)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun onDecrypt(encryptedString: String): String {
        val encryptedBytes = Base64.decode(encryptedString)
        val decryptedString: ByteArray = makeAes(encryptedBytes, Cipher.DECRYPT_MODE)
        return String(decryptedString)
    }
}