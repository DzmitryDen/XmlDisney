package com.example.disney_characters.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.xmldisney.databinding.FragmentDetailsBinding
import com.hfad.xmldisney.ui.details.DetailsViewModel
import com.hfad.xmldisney.ui.details.adapter.heroAdapter.DisneyHeroAdapter
import com.hfad.xmldisney.util.loadUrl
import dagger.hilt.android.AndroidEntryPoint

private const val ID = "id"

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()
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

        arguments?.let {
            viewModel.getCharacter(it.getInt(ID))
        }
        binding?.backBtn?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    companion object {
        fun getInstance(id: Int): DetailsFragment {
            return DetailsFragment().apply {
                arguments = bundleOf(ID to id)
            }
        }
    }
}