package com.example.recipeapp_w41

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity2 : AppCompatActivity() {

    private lateinit var rcAdapter : RecyclerView

    val items = arrayListOf<RecipeDetails.Datum>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        rcAdapter=findViewById(R.id.rvMain)
        rcAdapter.adapter=RVAdapter(this.items)
        rcAdapter.layoutManager=LinearLayoutManager(this)

        getRecipes()
    }
    private fun getRecipes() {
        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
            apiInterface?.getRecipies()?.enqueue(object : Callback<List<RecipeDetails.Datum>> {
                override fun onResponse(
                    call: Call<List<RecipeDetails.Datum>>,
                    response: Response<List<RecipeDetails.Datum>>
                ) {
                    for(r in response.body()!!){
                        val title = r.title
                        val name = r.author
                        val inq=r.ingredients
                        val ins = r.instructions
                        items.add(RecipeDetails.Datum(title,name,inq,ins))

                    }
                    rvMain.adapter!!.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<List<RecipeDetails.Datum>>, t: Throwable) {
                   // onResult(null)
                    Toast.makeText(applicationContext, ""+t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }


}
