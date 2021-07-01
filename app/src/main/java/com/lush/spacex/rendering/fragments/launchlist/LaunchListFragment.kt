package com.lush.spacex.rendering.fragments.launchlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lush.spacex.databinding.FragmentListBinding
import com.lush.spacex.rendering.adapters.LaunchListAdapter
import com.lush.spacex.rendering.displayItems.LaunchesDisplayState
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class LaunchListFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModelProvider: ViewModelProvider
    private lateinit var launchListViewModel: LaunchListViewModel

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

        launchListViewModel = viewModelProvider.get(LaunchListViewModel::class.java)

        val binding = FragmentListBinding.inflate(inflater, container, false)

        val launchListAdapter = LaunchListAdapter { launchDisplayItem ->
            launchListViewModel.onLaunchDisplayItemClicked(launchDisplayItem)
        }

        binding.listRecycler.adapter = launchListAdapter

        launchListViewModel.launchesLoadState.observe(viewLifecycleOwner) { launchesLoadState ->
            when (launchesLoadState) {
                is LaunchesDisplayState.Loading -> {
                    binding.progress.root.visibility = View.VISIBLE
                    binding.error.root.visibility = View.GONE
                    binding.listRecycler.visibility = View.GONE
                }
                is LaunchesDisplayState.Failed -> {
                    binding.progress.root.visibility = View.GONE
                    binding.error.root.visibility = View.VISIBLE
                    binding.listRecycler.visibility = View.GONE
                }
                is LaunchesDisplayState.Success -> {
                    binding.progress.root.visibility = View.GONE
                    binding.error.root.visibility = View.GONE
                    binding.listRecycler.visibility = View.VISIBLE
                    launchListAdapter.submitList(launchesLoadState.items)
                }
            }
        }

        return binding.root
    }
}