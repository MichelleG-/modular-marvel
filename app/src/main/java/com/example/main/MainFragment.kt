package com.example.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.ui.NavigationUI
import com.example.R
import com.example.commons.ui.base.BaseFragment
import com.example.commons.ui.extensions.setupWithNavController
import com.example.databinding.MainFragmentBinding
import com.example.datasource.di.util.ThemeUtils
import com.example.main.di.DaggerMainComponent
import com.example.main.di.MainModule
import com.example.main.menu.ToggleThemeCheckbox
import com.example.sample.SampleApp
import javax.inject.Inject

private const val DELAY_TO_APPLY_THEME = 1000L

class MainFragment : BaseFragment<MainFragmentBinding, MainViewModel>(
        layoutId = R.layout.main_fragment
) {

    @Inject
    lateinit var themeUtils: ThemeUtils

    private val navGraphIds = listOf(
            R.navigation.navigation_app_graph,
            R.navigation.navigation_character_favorite
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)

        menu.findItem(R.id.menu_toggle_theme).apply {
            val actionView = this.actionView
            if (actionView is ToggleThemeCheckbox) {
                actionView.isChecked = themeUtils.isDarkTheme(requireContext())
                actionView.setOnCheckedChangeListener { _, isChecked ->
                    themeUtils.setNightMode(isChecked, DELAY_TO_APPLY_THEME)
                }
            }
        }
    }

    override fun onInitDependencyInjection() {
        DaggerMainComponent
            .builder()
            .dataSourceComponent(SampleApp.dataSourceComponent(requireContext()))
            .mainModule(MainModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    private fun setupToolbar() {
        setHasOptionsMenu(true)
        requireCompatActivity().setSupportActionBar(viewBinding.toolbar)
    }

    private fun setupBottomNavigationBar() {
        val navController = viewBinding.bottomNavigation.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.nav_host_container,
                intent = requireActivity().intent
        )

        navController.observe(
                viewLifecycleOwner,
                Observer {
                    viewModel.navigationControllerChanged(it)
                    NavigationUI.setupActionBarWithNavController(requireCompatActivity(), it)
                }
        )
    }
}