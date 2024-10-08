package com.hfad.xmldisney.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disney_characters.ui.details.DetailsFragment
import com.hfad.xmldisney.R
import com.hfad.xmldisney.databinding.FragmentHomeBinding
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.ui.home.adapter.DisneyAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var binding: FragmentHomeBinding? = null
    private var adapter: DisneyAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.disneyHeroes.observe(viewLifecycleOwner) { heroLists ->
            if (heroLists.isNotEmpty()) loadHerroes(heroLists)
        }
        viewModel.loadListData()
    }

    private fun loadHerroes(items: List<DisneyHeroList>) {
        binding?.run {
            recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = DisneyAdapter { id ->
                parentFragmentManager.beginTransaction()
                    .add(R.id.container, DetailsFragment.getInstance(id))
                    .addToBackStack(null)
                    .commit()
            }.also {
                recycleView.adapter = it
            }
            adapter?.submitList(items)
        }
    }
}