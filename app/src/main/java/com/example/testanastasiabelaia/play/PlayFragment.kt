package com.example.testanastasiabelaia.play

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SimpleOnItemTouchListener
import com.example.testanastasiabelaia.R
import com.example.testanastasiabelaia.databinding.PlayFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PlayFragment : Fragment() {
    private var _binding: PlayFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val BASE_SCROLLING_COUNT = 50
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PlayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(binding.firstColumn)
        setupRecyclerView(binding.secondColumn)
        setupRecyclerView(binding.thirdColumn)
        binding.clicksCatcher.setOnTouchListener { _, _ -> true }
        binding.spinButton.setOnClickListener {
            val player = MediaPlayer.create(requireContext(), R.raw.spin_sound)
            player.start()
            binding.firstColumn.scrollFor(BASE_SCROLLING_COUNT + (1..10).random())
            binding.secondColumn.scrollFor(BASE_SCROLLING_COUNT + (15..25).random())
            binding.thirdColumn.scrollFor(BASE_SCROLLING_COUNT + (30..40).random())
            binding.spinButton.isEnabled = false
            lifecycleScope.launch(Dispatchers.IO) {
                delay(2500)
                withContext(Dispatchers.Main) {
                    binding.spinButton.isEnabled = true
                }
            }
            binding.spinButton.isEnabled = false
        }
    }

    private fun setupRecyclerView(recycler: RecyclerView) {
        val adapter = PlayAdapter(
            listOf(
                R.drawable.heart_img,
                R.drawable.bubna_img,
                R.drawable.clever_img,
                R.drawable.tref_img,
                R.drawable.horseshoe_img,
                R.drawable.star_img
            )
        )
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addOnItemTouchListener(object : SimpleOnItemTouchListener() {
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                return true
            }
        })
    }
}