package com.refaat.themoviesdb.ui.home.tabLayoutPages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.FragmentHomeBinding
import com.refaat.themoviesdb.databinding.FragmentNowPlayingBinding


class NowPlayingFragment : Fragment() {


    private var _binding: FragmentNowPlayingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}