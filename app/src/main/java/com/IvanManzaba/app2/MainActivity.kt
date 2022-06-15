package com.IvanManzaba.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    public fun obtenerDatos(view: View) {
        var json: Json
        val gson = Gson()
        val textview = findViewById<TextView>(R.id.txvDatos)
        textview.text =""
        val api = Volley.newRequestQueue(this)
        val url = "https://gorest.co.in/public/v1/users"
        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> { response ->
            val aux = gson.fromJson<Json>(response, Json::class.java).data
            if (aux != null) {
                for (item in aux) {
                    textview.text = textview.text.toString() + "Id: " + item.id + ", Name: " + item.name + ", Email: " + item.email + ", Gender: " + item.gender + ", Status: " + item.status + "\n"
                }
            }

        }, Response.ErrorListener { textview.text = "Error en la obtención de los datos" })
        api.add(request)

    }

}

data class Users(
    val id: Int,
    val name: String,
    val email: String,
    val gender: String,
    val status: String
)

class Json {
    val meta = null
    val data: ArrayList<Users>? = null
}