package com.shawn.githubdemo.utils

import android.widget.Toast
import com.shawn.githubdemo.GitHubDemoApplication


fun String.showToast() {
    Toast.makeText(
        GitHubDemoApplication.applicationContext(),
        this,
        Toast.LENGTH_SHORT
    ).show()
}