package com.refaat.themoviesdb.ui.favorites

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.FragmentFavoritesBinding
import com.refaat.themoviesdb.domain.model.Movie
import com.refaat.themoviesdb.ui.home.HomeFragmentDirections
import com.refaat.themoviesdb.ui.search.SearchFragmentDirections


class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.favorites_menu, menu)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        clicksConfig()

        return binding.root
    }

    private fun clicksConfig() {

//        binding.btnDetail.setOnClickListener {
//            val direction: NavDirections =
//                FavoritesFragmentDirections.actionFavoritesFragmentToDetailFragment(Movie())
//            Navigation.findNavController(it).navigate(direction)
//        }

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> {
            Toast.makeText(this.context,"Deleting all favorites...",Toast.LENGTH_SHORT).show()
            }
        }
        return super.onOptionsItemSelected(item)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}