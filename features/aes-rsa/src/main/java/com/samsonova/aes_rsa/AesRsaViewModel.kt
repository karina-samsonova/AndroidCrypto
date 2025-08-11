package com.samsonova.aes_rsa

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import java.security.Key
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.NoSuchAlgorithmException
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class AesRsaViewModel : ViewModel() {
    private var symmetricKey: SecretKey? = null
    private var keyPair: KeyPair? = null

    init {
        try {
            val keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(256)
            symmetricKey = keyGenerator.generateKey()

            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            keyPair = keyPairGenerator.generateKeyPair()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
    }

    @SuppressLint("GetInstance")
    fun makeAes(rawMessage: ByteArray?, cipherMode: Int): ByteArray {
        try {
            val cipher = Cipher.getInstance("AES")
            cipher.init(cipherMode, symmetricKey)
            val output = cipher.doFinal(rawMessage)
            return output
        } catch (e: Exception) {
            e.printStackTrace()
            return byteArrayOf()
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
            makeAes(targetString.toByteArray(), Cipher.ENCRYPT_MODE)

        val encodedSymmetricKey = makeRsa(symmetricKey?.encoded, Cipher.ENCRYPT_MODE, keyPair?.public)
        symmetricKey = SecretKeySpec(encodedSymmetricKey, 0, encodedSymmetricKey.size, "AES")

        return Base64.encode(encryptedBytes)
    }

    @OptIn(ExperimentalEncodingApi::class)
    fun onDecrypt(encryptedString: String): String {
        val decodedSymmetricKey = makeRsa(symmetricKey?.encoded, Cipher.DECRYPT_MODE, keyPair?.private)
        symmetricKey = SecretKeySpec(decodedSymmetricKey, 0, decodedSymmetricKey.size, "AES")

        val encryptedBytes = Base64.decode(encryptedString)
        val decryptedString: ByteArray = makeAes(encryptedBytes, Cipher.DECRYPT_MODE)

        return String(decryptedString)
    }
}