package com.lush.spacex.rendering.fragments.rocketdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.lush.spacex.databinding.FragmentRocketDetailBinding

class RocketDetailFragment : Fragment() {

    private lateinit var rocketDetailViewModel: RocketDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        rocketDetailViewModel =
            ViewModelProvider(this)
                .get(RocketDetailViewModel::class.java)

        val binding = FragmentRocketDetailBinding.inflate(inflater, container, false)

        return binding.root
    }
}