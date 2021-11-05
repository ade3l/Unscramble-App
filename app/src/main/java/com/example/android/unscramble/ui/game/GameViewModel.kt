package com.example.android.unscramble.ui.game

import android.util.Log
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    init {
        Log.d("GameFragment", "GameViewModel created!")
        getNextWord()
    }
    private var score = 0
    private var _currentWordCount = 0
    private lateinit var _currentScrambledWord : String
    val currentScrambledWord :String
        get() = _currentScrambledWord
    val currentWordCount : Int
        get()= _currentWordCount
    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragment", "GameViewModel destroyed!")
    }
    private var wordsList: MutableList<String> = mutableListOf()
    private lateinit var currentWord: String
    fun getNextWord() {
        currentWord= allWordsList.random()
        val tempWord= currentWord.toCharArray()
        tempWord.shuffle()
        while (String(tempWord).equals(currentWord,false)){
            tempWord.shuffle()
        }
        if(wordsList.contains(currentWord)){
            getNextWord()
        }else{
            _currentScrambledWord=String(tempWord)
            ++_currentWordCount
            wordsList.add(currentWord)
        }
    }

    fun nextWord():Boolean {
        return if(currentWordCount< MAX_NO_OF_WORDS){
            getNextWord()
            true
        }
        else false
    }

}