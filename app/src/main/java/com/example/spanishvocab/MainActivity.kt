package com.example.spanishvocab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spanishvocab.R.menu.main_menu
import com.example.spanishvocab.databinding.ActivityMainBinding
import java.io.InputStream
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerview.layoutManager = layoutManager
        binding.mainRecyclerview.setHasFixedSize(true)

        val adapter = MainAdapter()
        binding.mainRecyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val wordListSize = binding.mainRecyclerview.adapter!!.itemCount
        if (item.itemId == R.id.menu_item_top) {
            binding.mainRecyclerview.scrollToPosition(0)
            return true
        } else if (item.itemId == R.id.menu_item_bottom) {
            binding.mainRecyclerview.scrollToPosition(wordListSize - 1)
            return true
        } else {
            val offset = wordListSize - (0..wordListSize).random()
            binding.mainRecyclerview.scrollToPosition(offset)
            return true
        }
    }

    inner class MainViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        fun setText(text : String) {
           view.findViewById<TextView>(R.id.item_textview)
               .setText(text)
        }
    }

    inner class MainAdapter() : RecyclerView.Adapter<MainViewHolder>() {
        var spanishWordList = mutableListOf<String>()
        var englishWordList = mutableListOf<String>()

        init {
            val input : InputStream = getAssets().open("spanish_words.txt")
            val scanner = Scanner(input)
            while(scanner.hasNextLine()){
                spanishWordList.add(scanner.nextLine().toString())
                englishWordList.add(scanner.nextLine().toString())
                scanner.nextLine()
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false)
            return MainViewHolder(view)
        }

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
           holder.setText(spanishWordList[position])
        }

        override fun getItemCount(): Int {
            return spanishWordList.size;
        }
    }
}