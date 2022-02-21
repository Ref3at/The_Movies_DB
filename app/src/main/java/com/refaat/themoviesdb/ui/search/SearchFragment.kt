package com.refaat.themoviesdb.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.refaat.themoviesdb.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        clicksConfig()
        return binding.root

    }

    private fun clicksConfig() {

        binding.btnDetail.setOnClickListener {
//            val direction: NavDirections =
//                SearchFragmentDirections.actionSearchFragmentToDetailFragment()
//            Navigation.findNavController(it).navigate(direction)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}