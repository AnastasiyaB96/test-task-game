package com.example.testanastasiabelaia.play

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testanastasiabelaia.R
import com.example.testanastasiabelaia.databinding.PlayFragmentBinding
import com.example.testanastasiabelaia.views.SlotRecyclerView

class PlayFragment : Fragment() {
    private var _binding: PlayFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PlayFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(binding.firstColumn)
        setupRecyclerView(binding.secondColumn)
        setupRecyclerView(binding.thirdColumn)
        var currentPosition = 0
        binding.root.setOnClickListener {
            currentPosition+=50

            binding.firstColumn.scrollTo(currentPosition+(1..10).random())
            binding.secondColumn.scrollTo(currentPosition+(15..25).random())
            binding.thirdColumn.scrollTo(currentPosition+(30..40).random())
        }
    }

    fun setupRecyclerView(recycler: RecyclerView){
        val adapter = PlayAdapter(listOf(R.drawable.black, R.drawable.red, R.drawable.blue))
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }
}