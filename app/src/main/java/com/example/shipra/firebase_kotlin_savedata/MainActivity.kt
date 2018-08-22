package com.example.shipra.firebase_kotlin_savedata

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var editText : EditText
    lateinit var rating :RatingBar
    lateinit var save :Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText=findViewById(R.id.edt_name)
        rating=findViewById(R.id.rt_bar)
        save=findViewById(R.id.btn_save)


        save.setOnClickListener{

            saveStars()

        }


    }

    private fun saveStars() {

        val name = edt_name.toString().trim()

        if(name.isEmpty())
        {
            editText.error="please enter the name"
        }

        val ref = FirebaseDatabase.getInstance().getReference("star")



        val starId=ref.push().key

        val star=stars(starId,name,rt_bar.numStars)
        ref.child(starId).setValue(star).addOnCompleteListener{

          Toast.makeText(applicationContext,"stars saved successfuly",Toast.LENGTH_SHORT).show()


        }



    }
}
