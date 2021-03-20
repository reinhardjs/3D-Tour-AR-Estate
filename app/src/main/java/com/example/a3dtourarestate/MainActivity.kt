package com.example.a3dtourarestate

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.unity3d.tour3d.UnityPlayerActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, UnityPlayerActivity::class.java)
        intent.putExtra("modelUrl", "https://gitlab.com/reinhardjonathansilalahi/3dmodel/-/raw/master/3dmodel.glb");

        startActivity(intent)
    }
}