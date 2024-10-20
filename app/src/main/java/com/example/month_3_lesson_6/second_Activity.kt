package com.example.month_3_lesson_6

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.month_3_lesson_6.databinding.ActivityMainBinding
import com.example.month_3_lesson_6.databinding.ActivitySecondBinding

class second_Activity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondBinding.inflate(layoutInflater)

        val v = binding.root

        setContentView(v)
        val img = intent.getStringExtra("uri")
        val text = intent.getStringExtra("msg")
        if (img!=null){
            val uri = Uri.parse(img)
            binding.iv.setImageURI(uri)
}
        if(text !=null){
            binding.tv.setText(text)
        }
    }
}