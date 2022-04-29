package com.example.app_sem6_netw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class JokeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joke)


        val btJoke = findViewById<Button>(R.id.btJoke)

        btJoke.setOnClickListener {
            loadJoke()
        }
    }

    private fun loadJoke() {
        val tvJoke = findViewById<TextView>(R.id.tvJoke)
        //tvJoke.text = "Broma encontrada!!!"
        //creo una instancia de retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://geek-jokes.sameerkumar.website/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //creo una instancia de JokeService
        val jokeService: JokeService
        jokeService = retrofit.create(JokeService::class.java)

        val request = jokeService.getJoke("json")

        request.enqueue(object : Callback<Joke>{
            override fun onResponse(call: Call<Joke>, response: Response<Joke>) {
                if (response.isSuccessful)
                {
                    tvJoke.text = response.body()!!.joke
                }
                else{
                    //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@JokeActivity, "erooronR", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Joke>, t: Throwable) {
                Toast.makeText(this@JokeActivity, "erooronF", Toast.LENGTH_LONG).show()
            }

        })
    }
}