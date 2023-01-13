package com.example.android.unscramble.ui.explanation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.android.unscramble.R
import com.example.android.unscramble.databinding.ExplanationFragmentBinding
import com.example.android.unscramble.model.GameViewModel

class ExplanationFragment : Fragment() {
    private lateinit var binding : ExplanationFragmentBinding
    private val sharedViewModel : GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.explanation_fragment, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.sharedViewModel = sharedViewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }
}