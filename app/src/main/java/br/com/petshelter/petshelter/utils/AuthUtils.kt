package br.com.petshelter.petshelter.utils

import android.content.Context
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

fun FirebaseAuth.isUserLoggedIn() = currentUser != null

inline fun Context.signOut(crossinline singOutAction: () -> Unit) =
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener { singOutAction() }
