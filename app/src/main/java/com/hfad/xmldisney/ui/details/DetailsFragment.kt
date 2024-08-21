package com.hfad.xmldisney.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.xmldisney.databinding.FragmentDetailsBinding
import com.hfad.xmldisney.ui.details.adapter.heroAdapter.DisneyHeroAdapter
import com.hfad.xmldisney.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
    private val arguments: DetailsFragmentArgs by navArgs()
    private var binding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.hero.observe(viewLifecycleOwner) { character ->
            binding?.run {
                characterName.text = character?.name
                character?.image?.let { characterImg.loadUrl(it) }
            }
            character?.fields?.let {
                val adapter = DisneyHeroAdapter()
                binding?.run {
                    rwFields.layoutManager = LinearLayoutManager(requireContext())
                    rwFields.adapter = adapter
                    adapter.submitList(it)
                }
            }
        }
        viewModel.isSavedHero(arguments.id)
        binding?.backBtn?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.like?.setOnClickListener {
            viewModel.likeHerro(arguments.id)
        }
    }
}