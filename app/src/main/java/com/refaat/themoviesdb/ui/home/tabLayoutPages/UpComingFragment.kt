package com.refaat.themoviesdb.ui.home.tabLayoutPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.FragmentHomeBinding
import com.refaat.themoviesdb.databinding.FragmentUpComingBinding


class UpComingFragment : Fragment() {

    private var _binding: FragmentUpComingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpComingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
