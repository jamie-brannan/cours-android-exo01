package eemi.tp1.coursexo01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.widget.*
import org.json.JSONObject
import org.json.JSONTokener
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var up_count = findViewById<Button>(R.id.increase)
        up_count.setOnClickListener{ v ->
//            var t = findViewById<TextView>(R.id.counter_text)
//            println("clicked")
//            t.text = "Hello right back!"
//
//            var i = Intent(this, SecondActivity::class.java)
//            i.putExtra("date", Date().time)
//            startActivity(i)
//
//            finish()

            // Thread resolves the async issue on the main
            val t = Thread(Runnable{
                // Need our source
                var url = URL("http://www.jdecool.fr/feed/feed.json")

                // Connection is going to find a way to connect to the url, could be via many different ways to talk to that host with it's protocol
                var connection = url.openConnection()

                // What is in the stream? Everything you can read from the socket (header, error, codes, many things). Where you're going to read your stuff
                var stream = connection.getInputStream()

                // Buffere reader is something that transforms data into strings
                var buf = BufferedReader(InputStreamReader(stream))

//                var json = JsonReader(InputStreamReader(stream))
//                json.nextString()

                var dataStr = ""
                var lineStr = buf.readLine()
                while (lineStr != null) {
                    dataStr = dataStr + '\n' + lineStr
                    lineStr = buf.readLine()
                }

//                var t = JSONTokener
                var o = JSONObject(dataStr)
//                Log.e("test", o.toString())

                // can explore structure of the JSON that you're receiving, you need

                // Know it's an array, get an object from the array items
                var items = o.getJSONArray("items")
                for (i in 0..(items.length()-1)) {
                    var line = items.getJSONObject(i)
                    Log.e("test",line.getString( "title"))
                }

                // console log your lines
//                Log.e("test", buf.readLine())
//                Log.e("test", json.nextString())
            })
            t.start()
        }
    }
}
