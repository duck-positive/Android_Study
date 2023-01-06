/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.unscramble.ui.game

import android.text.Spannable
import android.text.SpannableString
import android.text.style.TtsSpan
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

/**
 * ViewModel containing the app data and methods to process the data
 */
class GameViewModel : ViewModel() {

    private var wordList : MutableList<String> = mutableListOf()
    private lateinit var currentWord : String
    private lateinit var _currentScrambledWord : String

    private var _score = 0
    private var _currentWordCount = 0

    val currentScrambledWord : String
        get() = _currentScrambledWord
    val currentWordCount : Int
        get() = _currentWordCount
    val score : Int
        get() = _score

    init {
        Log.d("GameFragment", "viewmodel create")
        getNextWord()
    }

    private fun getNextWord(){
        currentWord = allWordsList.random()
        val tempWord = currentWord.toCharArray()
        while(String(tempWord).equals(currentWord, false)) {
            tempWord.shuffle()
        }
        if(wordList.contains(currentWord)){
            getNextWord()
        } else {
            _currentScrambledWord = String(tempWord)
            ++_currentWordCount
            wordList.add(currentWord)
        }

    }

    private fun increaseScore(){
        _score += SCORE_INCREASE
    }

    fun isUserWordCorrect(playerWord : String) : Boolean {
        if(playerWord.equals(currentWord, true)){
            increaseScore()
            return true
        }
        return false
    }

    fun nextWord() : Boolean {
        return if(currentWordCount < MAX_NO_OF_WORDS){
            getNextWord()
            true
        } else false
    }

    fun reinitializeData() {
        _score = 0
        _currentWordCount = 0
        wordList.clear()
        getNextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("GameFragement", "viewmodel destroy")
    }
}
