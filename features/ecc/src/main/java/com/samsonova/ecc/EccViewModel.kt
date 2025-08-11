package com.samsonova.ecc

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.Security
import javax.crypto.Cipher
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class EccViewModel : ViewModel() {
    private var keyPair: KeyPair? = null

    init {
        try {
            Security.addProvider(BouncyCastleProvider())
            val keyPairGenerator = KeyPairGenerator.getInstance("EC")
            keyPairGenerator.initialize(256)
            keyPair = keyPairGenerator.generateKeyPair()
        } catch (e: Exception) {
            Log.e("Crypto", "Key pair error")
        }
    }

    @SuppressLint("GetInstance")
    fun makeEcc(rawMessage: ByteArray?, cipherMode: Int, key: Key?): ByteArray {
        try {
            val cipher = Cipher.getInstance("ECIES", BouncyCastleProvider())
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
            makeEcc(targetString.toByteArray(), Cipher.ENCRYPT_MODE, keyPair?.public)
        return Base64.encode(encryptedBytes)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun onDecrypt(encryptedString: String): String {
        val encryptedBytes = Base64.decode(encryptedString)
        val decryptedString: ByteArray =
            makeEcc(encryptedBytes, Cipher.DECRYPT_MODE, keyPair?.private)
        return String(decryptedString)
    }
}