package com.example.spanishvocab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.spanishvocab.databinding.ActivityWordBinding

class WordActivity : AppCompatActivity() {
    private lateinit var binding : ActivityWordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        val wordTranslation = intent.getSerializableExtra(
            getString(R.string.word_translation_intent_key)
        ) as WordTranslation

        binding.spanishWord.setText(wordTranslation.spanishWord)
    }
}