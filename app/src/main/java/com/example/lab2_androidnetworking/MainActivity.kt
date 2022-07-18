package com.example.lab2_androidnetworking

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab2_androidnetworking.databinding.ActivityMainBinding
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFetch.setOnClickListener {
            LoadImage().execute("https://dogecoin.com/assets/img/doge.png")
        }
    }

    inner class LoadImage : AsyncTask<String, Void, Bitmap>() {
        override fun doInBackground(vararg strings: String?): Bitmap? {
            try {
                return BitmapFactory.decodeStream(URL(strings[0]).content as InputStream)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
//                binding.txtError.text = "Fetch unsuccessful"
            }
            return null;
        }

        override fun onPostExecute(result: Bitmap?) {
            super.onPostExecute(result)
            result?.let {
                binding.image.setImageBitmap(result)
                binding.txtError.text = "Fetch successfully"
            }
        }
    }
}

