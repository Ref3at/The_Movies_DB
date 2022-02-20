package com.refaat.themoviesdb.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                val direction: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToAboutFragment()
                findNavController(this).navigate(direction)
            }
            R.id.action_search -> {
                val direction: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToSearchFragment()
                findNavController(this).navigate(direction)
            }
            R.id.action_favorites -> {
                val direction: NavDirections =
                    HomeFragmentDirections.actionHomeFragmentToFavoritesFragment()
                findNavController(this).navigate(direction)
            }
        }
        return super.onOptionsItemSelected(item)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}