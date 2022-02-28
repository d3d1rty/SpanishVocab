package com.example.spanishvocab

import java.io.Serializable

class WordTranslation(var spanishWord : String = "", var englishWord : String = "") : Serializable {
    override fun toString(): String {
        return "WordTranslation(spanishWord=${spanishWord} englishWord=${englishWord}"
    }
}