package eemi.tp1.coursexo01

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_second.view.*

class SecondActivity : AppCompatActivity() {

    // this is executed when a new object of this type is created
    // why null ? Can't talk about this because this doesn't exist -> a recursive problem
    var adapter: MyListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

//        val i = intent.getLongExtra("date", 0)
//        findViewById<TextView>(R.id.hello02)

        // Adapters context is whatever screen it's in, it's an array
        adapter = MyListAdapter(this, this)

        for(i in 1..100) {
            adapter?.add("Yo")
        }

        // adapter could be null
        adapter?.add("Hello")
        adapter?.add("Good day")
        adapter?.add("Good bye")

        // get your list view via it's id with this adapter for the screen
        findViewById<ListView>(R.id.testlist).adapter = adapter

        // adapter view is the list view itself
        // may have tapped somewhere with in the view so view
        // i is the index, so the row index
        // l is listener, so it's going to be self
        findViewById<ListView>(R.id.testlist).setOnItemClickListener{ adapterView, view, i, l ->
            Log.e("TEST", "tapped on $i")
        }
    }

    class MyListAdapter(context: Context, activity : Activity) : ArrayAdapter<String>(context, R.id.main){
        var parentActivity = activity

        // Am I reusing a view or creating a new one, why context is important
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var result : View
            if (convertView != null) {
                result = convertView
            } else {
                // method 1 : have system get the inflater - probably
                val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                // method 2: bad design, can't be sure that context is always a second activity, ArrayAdaptor in different activities, then you're fucked
                val inflater2 = parentActivity.layoutInflater

                result = inflater2.inflate(R.layout.line, null)
            }
            result.findViewById<TextView>(R.id.main).text = this.getItem(position)
            result.findViewById<TextView>(R.id.counter).text = "$position"

            result.minimumHeight = 100

            return result
        }
    }


}
