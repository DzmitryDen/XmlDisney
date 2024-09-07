package com.hfad.xmldisney.ui.home

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hfad.xmldisney.App
import com.hfad.xmldisney.R
import com.hfad.xmldisney.databinding.FragmentHomeBinding
import com.hfad.xmldisney.models.DisneyHeroList
import com.hfad.xmldisney.ui.home.adapter.DisneyAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {

    @Inject
    lateinit var vmProvider: HomeVMProvider
    private val viewModel: HomeViewModel by viewModels { vmProvider }
    private var binding: FragmentHomeBinding? = null
    private var adapter: DisneyAdapter? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        App.appComponent?.inject(this)
    }

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
            if (heroLists != null) {
                loadHeroes(heroLists)
                setBannerState(
                    getDrawable(requireContext(), R.drawable.icon_success),
                    "ok",
                    requireContext().getColor(R.color.green)
                )
            } else {
                loadHeroes(arrayListOf())
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                setBannerState(
                    getDrawable(requireContext(), R.drawable.icon_error),
                    message,
                    requireContext().getColor(R.color.red)
                )
            }
        }
        viewModel.loadListData()

        binding?.allHero?.setOnClickListener {
            viewModel.showAll()
        }

        binding?.myHero?.setOnClickListener {
            viewModel.showFavorite()
        }

        binding?.banner?.close {
            binding?.banner?.visibility = View.GONE
        }
    }

    private fun loadHeroes(items: List<DisneyHeroList>) {
        binding?.run {
            recycleView.layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = DisneyAdapter { id ->
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
                        id
                    )
                )
            }.also {
                recycleView.adapter = it
            }
            adapter?.submitList(items)
        }
    }

    private fun setBannerState(icon: Drawable?, message: String, color: Int) {
        binding?.banner?.run {
            visibility = View.VISIBLE
            setIcon(icon)
            setMessage(message)
            setBackground(color)
        }
    }
}