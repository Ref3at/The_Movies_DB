package com.refaat.themoviesdb.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayoutMediator
import com.refaat.themoviesdb.R
import com.refaat.themoviesdb.databinding.FragmentHomeBinding
import com.refaat.themoviesdb.ui.home.tabLayoutPages.NowPlayingFragment
import com.refaat.themoviesdb.ui.home.tabLayoutPages.PopularFragment
import com.refaat.themoviesdb.ui.home.tabLayoutPages.TopRatedFragment
import com.refaat.themoviesdb.ui.home.tabLayoutPages.UpComingFragment

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        val fragmentList = arrayListOf(
            Pair("Now Playing", NowPlayingFragment()),
            Pair("Popular", PopularFragment()),
            Pair("Top Rated", TopRatedFragment()),
            Pair("Up Coming", UpComingFragment())
        )
        val homeFragmentStateAdapter = HomeFragmentStateAdapter(fragmentList, this)

        binding.viewPager.adapter = homeFragmentStateAdapter
        binding.viewPager.offscreenPageLimit = fragmentList.size
        binding.viewPager.isUserInputEnabled = false
        binding.viewPager.isHorizontalScrollBarEnabled = false


        fragmentList.forEach {
            val tab = binding.tabLayout.newTab()
            tab.text = it.first
            binding.tabLayout.addTab(tab)
        }

        binding.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.setCurrentItem(tab.position, false)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })







        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = fragmentList[position].first
        }.attach()
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