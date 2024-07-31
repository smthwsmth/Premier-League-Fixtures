package com.example.premierleaguefixtures.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.navigation.findNavController
import com.example.premierleaguefixtures.R
import com.example.premierleaguefixtures.ui.MainActivity
import com.example.premierleaguefixtures.ui.MatchesViewModel

class StartFragment : Fragment(), MenuProvider {

    lateinit var viewModel: MatchesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().addMenuProvider(this, viewLifecycleOwner)
        viewModel = (activity as MainActivity).viewModel

    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId) {
            R.id.download_and_save_to_db -> {
                view?.findNavController()?.navigate(
                    R.id.action_startFragment_to_mainPageFragment)
            }
        }
        return true
    }
}