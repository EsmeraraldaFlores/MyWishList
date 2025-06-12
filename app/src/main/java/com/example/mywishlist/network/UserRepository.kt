package com.example.mywishlist.network


import com.example.mywishlist.core.ResultWrapper
import com.example.mywishlist.core.safeCall
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject



class UserRepository @Inject constructor(
private val firebaseAuth: FirebaseAuth
){
    suspend fun login(email: String, password: String): ResultWrapper<FirebaseUser> = safeCall {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        result.user ?: throw Exception("usuario no encontrado")
    }

    suspend fun requestRegister(email: String, password: String): ResultWrapper<FirebaseUser> = safeCall {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        result.user ?: throw Exception("No se pudo crear el usuario")
    }

}