package eemi.tp1.coursexo01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var up_count = findViewById<Button>(R.id.increase)
        up_count.setOnClickListener{ v ->
            var t = findViewById<TextView>(R.id.counter_text)
            println("clicked")
            t.text = "Hello right back!"

            var i = Intent(this, SecondActivity::class.java)
            i.putExtra("date", Date().time)
            startActivity(i)

            finish()
        }
    }
}
