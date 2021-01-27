package com.example.characterslist.list.ui

import android.os.Bundle
import android.view.View
import androidx.paging.PagedList
import com.example.characterslist.R
import com.example.characterslist.databinding.FragmentCharactersListBinding
import com.example.characterslist.list.di.CharactersListModule
import com.example.characterslist.list.model.CharacterItem
import com.example.characterslist.list.ui.adapter.CharactersListAdapter
import com.example.characterslist.list.ui.adapter.CharactersListAdapterState
import com.example.commons.ui.base.BaseFragment
import com.example.commons.ui.extensions.observe
import javax.inject.Inject
import androidx.navigation.fragment.findNavController
import com.example.characterslist.list.di.DaggerCharactersListComponent
import com.example.commons.ui.extensions.gridLayoutManager
import com.example.sample.SampleApp


/**
 * View listing the all marvel characters with option to display the detail view.
 *
 * @see BaseFragment
 */
class CharactersListFragment :
    BaseFragment<FragmentCharactersListBinding, CharactersListViewModel>(
        layoutId = R.layout.fragment_characters_list
    ) {

    @Inject
    lateinit var viewAdapter: CharactersListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        observe(viewModel.data, ::onViewDataChange)
        observe(viewModel.event, ::onViewEvent)
    }

    override fun onInitDependencyInjection() {
        DaggerCharactersListComponent
            .builder()
            .dataSourceComponent(SampleApp.dataSourceComponent(requireContext()))
            .charactersListModule(CharactersListModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersList.apply {
            adapter = viewAdapter
            gridLayoutManager?.spanSizeLookup = viewAdapter.getSpanSizeLookup()
        }
    }

    private fun onViewDataChange(viewData: PagedList<CharacterItem>) {
        viewAdapter.submitList(viewData)
    }

    private fun onViewStateChange(viewState: CharactersListViewState) {
        when (viewState) {
            is CharactersListViewState.Loaded ->
                viewAdapter.submitState(CharactersListAdapterState.Added)
            is CharactersListViewState.AddLoading ->
                viewAdapter.submitState(CharactersListAdapterState.AddLoading)
            is CharactersListViewState.AddError ->
                viewAdapter.submitState(CharactersListAdapterState.AddError)
            is CharactersListViewState.NoMoreElements ->
                viewAdapter.submitState(CharactersListAdapterState.NoMore)
        }
    }

    /**
     * Observer view event change on [CharactersListViewModel].
     *
     * @param viewEvent Event on characters list.
     */
    private fun onViewEvent(viewEvent: CharactersListViewEvent) {
        when (viewEvent) {
            is CharactersListViewEvent.OpenCharacterDetail ->
                findNavController().navigate(
                    CharactersListFragmentDirections
                        .actionCharactersListFragmentToCharacterDetailFragment(viewEvent.id)
                )
        }
    }
}
