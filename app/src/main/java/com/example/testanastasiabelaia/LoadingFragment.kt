package com.example.testanastasiabelaia

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.testanastasiabelaia.databinding.LoadingFragmentBinding
import com.example.testanastasiabelaia.play.PlayFragment


class LoadingFragment : Fragment() {
    private val viewModel: LoadingViewModel by viewModels()

    private var _binding: LoadingFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getShowGame(
            requireContext().getSharedPreferences(
                "default",
                Context.MODE_PRIVATE
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoadingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupWebView()
        viewModel.showGame.observe(viewLifecycleOwner) {
            when (it) {
                LoadingResult.SHOW_GAME -> {
                    requireActivity()
                        .supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.loading_fragment, PlayFragment())
                        .commit()
                }
                LoadingResult.SHOW_WEB_VIEW -> {
                    binding.webView.visibility = View.VISIBLE
                }
                else -> {
                    Log.d("LOADING", "Waiting")
                }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        }
    }

    fun setupWebView() {
        binding.webView.loadUrl("https://www.reddit.com/")
        binding.webView.visibility = View.GONE
    }
}