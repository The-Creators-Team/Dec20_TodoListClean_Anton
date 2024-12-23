package com.creators.dec20_todolistclean_anton.data.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    val TAG : String = "AuthRepositoryImpl"

    suspend fun loginWithEmail(email: String, password: String): Boolean {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            false
        }
    }

    suspend fun signUpWithEmail(email: String, password: String): Boolean {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            true
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            false
        }
    }

    suspend fun resetPassword(email: String): Boolean {
        return try {
            firebaseAuth.sendPasswordResetEmail(email).await()
            true
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
            false
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }

    //todo: implement login with github and yahoo
}