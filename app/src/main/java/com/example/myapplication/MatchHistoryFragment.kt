package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MatchHistoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MatchHistoryFragment : Fragment() {
    private val viewModel: RackViewModel by viewModels()
    private lateinit var adapter: MatchHistoryAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_match_history, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.matchHistoryRecycler)

        adapter = MatchHistoryAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        lifecycleScope.launch {
            viewModel.allRacks.collect { racks ->
                adapter.setRacks(racks)
            }
        }
        return root
    }
}