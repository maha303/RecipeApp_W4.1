package com.example.recipeapp_w41

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Toast.makeText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var edTitle:EditText
    private lateinit var edName:EditText
    private lateinit var edIngredients:EditText
    private lateinit var edInstructions:EditText

    private lateinit var btnSave:Button
    private lateinit var btnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edTitle=findViewById(R.id.edTitle)
        edName=findViewById(R.id.edName)
        edIngredients=findViewById(R.id.edIngredients)
        edInstructions=findViewById(R.id.edInstructions)

        btnView=findViewById(R.id.btnView)
        btnView.setOnClickListener{
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }
        btnSave=findViewById(R.id.btnSave)
        btnSave.setOnClickListener{

            var f = RecipeDetails.Datum(edTitle.text.toString(), edName.text.toString(),
                edIngredients.text.toString(),edIngredients.text.toString())

            addReceipe(f, onResult = {
                edTitle.setText("")
                edName.setText("")
                edIngredients.setText("")
                edInstructions.setText("")
                makeText(this, "Save Success!", Toast.LENGTH_SHORT).show()
            })
        }
        }


    fun addReceipe(userData:RecipeDetails.Datum,onResult:(RecipeDetails?)->Unit){
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        apiInterface?.addRecipie(userData)?.enqueue(object : Callback<RecipeDetails> {
            override fun onResponse(
                call: Call<RecipeDetails>,
                response: Response<RecipeDetails>
            ) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<RecipeDetails>, t: Throwable) {
                onResult(null)
            }
        })

    }}
