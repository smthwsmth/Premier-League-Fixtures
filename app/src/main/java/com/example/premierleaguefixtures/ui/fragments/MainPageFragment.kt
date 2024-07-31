package com.example.premierleaguefixtures.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.MenuProvider
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.premierleaguefixtures.R
import com.example.premierleaguefixtures.data.models.MatchItem
import com.example.premierleaguefixtures.other.MatchAdapter
import com.example.premierleaguefixtures.ui.MainActivity
import com.example.premierleaguefixtures.ui.MatchesViewModel

class MainPageFragment : Fragment(R.layout.fragment_main_page), MenuProvider {

    lateinit var viewModel: MatchesViewModel
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(this, viewLifecycleOwner)


        viewModel = (activity as MainActivity).viewModel
        setRecyclerView(view)

        matchAdapter.setOnClickListener(object :
            MatchAdapter.OnClickListener {
            override fun onClick(position: Int, model: MatchItem) {

                val bundle = Bundle().apply {
                    putSerializable("match", model)
                }
                view.findNavController().navigate(
                    R.id.action_mainPageFragment_to_detailsFragment,
                    bundle
                )


            }

        })


        viewModel.allMatches.observe(viewLifecycleOwner, Observer { response ->

            response.let { newResponse ->
                matchAdapter.differ.submitList(newResponse)
            }
        })

        viewModel.searchMatches.observe(viewLifecycleOwner, Observer { response ->
            response.let { newResponse ->
                matchAdapter.differ.submitList(newResponse)
            }

        })

    }


    // ProgressBar will be used later
//    private fun hideProgressBar(view: View) {
//        view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
//    }
//
//    private fun showProgressBar(view: View) {
//        view.findViewById<ProgressBar>(R.id.progressBar).visibility =  View.VISIBLE
//    }

    private fun setRecyclerView(view: View) = view.findViewById<RecyclerView>(R.id.recyclerview)
        .apply {
            matchAdapter = MatchAdapter()
            adapter = matchAdapter
        }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
        val search = menu.findItem(R.id.search)
        val searchView = search.actionView as androidx.appcompat.widget.SearchView
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.searchMatch(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) {
                        viewModel.searchMatch(newText)
                    }
                    return true
                }
            }
        )
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.clean_db -> {
                viewModel.deleteAllMatches()
                view?.findNavController()?.navigate(
                    R.id.action_mainPageFragment_to_startFragment
                )
            }
            R.id.download_and_save_to_db -> {
                viewModel.getAllMatchesItems()
            }
        }
        return true
    }
}