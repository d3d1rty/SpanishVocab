package com.example.spanishvocab

import android.content.Intent
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
    var spanishWordList: MutableList<String> = mutableListOf<String>()
    var englishWordList: MutableList<String> = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val input : InputStream = getAssets().open("spanish_words.txt")
        val scanner = Scanner(input)
        while(scanner.hasNextLine()){
            spanishWordList.add(scanner.nextLine().toString())
            englishWordList.add(scanner.nextLine().toString())
            scanner.nextLine()
        }

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

    inner class MainViewHolder(val view : View) :
        RecyclerView.ViewHolder(view),
        View.OnClickListener {

        init {
            view.findViewById<View>(R.id.item_textview).setOnClickListener(this)
        }

        fun setText(text : String) {
           view.findViewById<TextView>(R.id.item_textview)
               .setText(text)
        }

        override fun onClick(view: View?) {
            if (view != null) {
                val intent = Intent(view.context, WordActivity::class.java)
                val spanishWord = spanishWordList[adapterPosition]
                val englishWord = englishWordList[adapterPosition]
                val wordTranslation = WordTranslation(spanishWord, englishWord)
                intent.putExtra(getString(R.string.word_translation_intent_key), wordTranslation)
                startActivity(intent)
            }
        }
    }

    inner class MainAdapter() : RecyclerView.Adapter<MainViewHolder>() {
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