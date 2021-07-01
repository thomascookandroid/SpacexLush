package com.lush.spacex.rendering.fragments.rocketlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lush.spacex.databinding.FragmentListBinding
import com.lush.spacex.rendering.adapters.RocketListAdapter
import com.lush.spacex.rendering.displayItems.RocketsDisplayState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class RocketListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var rocketListViewModel: RocketListViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModelProvider = ViewModelProvider(this, viewModelFactory)

        rocketListViewModel = viewModelProvider.get(RocketListViewModel::class.java)

        val binding = FragmentListBinding.inflate(inflater, container, false)

        val rocketListAdapter = RocketListAdapter { rocketDisplayItem ->
            rocketListViewModel.onRocketDisplayItemClicked(rocketDisplayItem)
        }

        binding.listRecycler.adapter = rocketListAdapter

        rocketListViewModel.rocketsLoadState.observe(viewLifecycleOwner) { rocketsLoadState ->
            when (rocketsLoadState) {
                is RocketsDisplayState.Loading -> {
                    binding.progress.root.visibility = View.VISIBLE
                    binding.error.root.visibility = View.GONE
                    binding.listRecycler.visibility = View.GONE
                }
                is RocketsDisplayState.Failed -> {
                    binding.progress.root.visibility = View.GONE
                    binding.error.root.visibility = View.VISIBLE
                    binding.listRecycler.visibility = View.GONE
                }
                is RocketsDisplayState.Success -> {
                    binding.progress.root.visibility = View.GONE
                    binding.error.root.visibility = View.GONE
                    binding.listRecycler.visibility = View.VISIBLE
                    rocketListAdapter.submitList(rocketsLoadState.items)
                }
            }
        }

        return binding.root
    }
}