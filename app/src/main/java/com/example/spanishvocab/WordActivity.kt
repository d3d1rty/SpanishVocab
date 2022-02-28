package com.example.spanishvocab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.spanishvocab.databinding.ActivityWordBinding

class WordActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityWordBinding
    lateinit var wordTranslation : WordTranslation
    private var displayImage : Int = R.drawable.ic_face_thinking_person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = getIntent()
        wordTranslation = intent.getSerializableExtra(
            getString(R.string.word_translation_intent_key)
        ) as WordTranslation

        binding.spanishWord.setText(wordTranslation.spanishWord)
        binding.imageView.setImageResource(displayImage)
        binding.checkWordButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val guess = binding.inputTextview.text.toString()
        if (guess == wordTranslation.englishWord) {
            displayImage = R.drawable.ic_teeth_face
        } else {
            displayImage = R.drawable.ic_face_disgusting
        }
        binding.imageView.setImageResource(displayImage)
    }
}