package com.example.month_3_lesson_6

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore

import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

import com.example.month_3_lesson_6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var uri: Uri? = null

    private val SELECT_PICTURE = 1


    private var selectedImagePath: String? = null

    private lateinit var binding: ActivityMainBinding

    private val getContentResult = registerForActivityResult(ActivityResultContracts.GetContent()) { selectedUri: Uri? ->
            if (selectedUri != null) {
                uri = selectedUri
                selectedImagePath = getPath(uri!!)
                binding.imageView.setImageURI(uri)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)


        binding.materialButtonGamil.setOnClickListener {
            if (!binding.gmailEditText.text.isNullOrEmpty()) {
                val gmailIntent = Intent(Intent.ACTION_VIEW)
                gmailIntent.data = Uri.parse("mailto:dastanovdanil@icloud.com")
                startActivity(gmailIntent)
            } else {
                binding.gmailEditText.error = "Email cannot be empty"
            }
        }

        binding.choosePicture.setOnClickListener {
                getContentResult.launch("image/*")
        }



        binding.goToSecond.setOnClickListener {
            val intent22 = Intent(this, second_Activity::class.java)
            if (binding.justEditText.text.isNullOrEmpty()) {
                binding.justEditText.error = "nothing to send"
            } else {
                val msg = binding.justEditText.text.toString()
                intent22.putExtra("uri",uri.toString())
                intent22.putExtra("msg",msg)
                startActivity(intent22)
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            val selectedImageUri = data?.data ?: return
            selectedImagePath = getPath(selectedImageUri)
        }
    }


    private fun getPath(uri: Uri): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, projection, null, null, null)

        return cursor?.use {
            if (it.moveToFirst()) {
                val columnIndex = it.getColumnIndex(MediaStore.Images.Media.DATA)
                it.getString(columnIndex)
            } else {
                null
            }
        }
    }
}