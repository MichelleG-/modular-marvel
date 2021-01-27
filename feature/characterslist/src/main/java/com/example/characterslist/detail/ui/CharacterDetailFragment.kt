package com.example.characterslist.detail.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.sample.SampleApp.Companion.dataSourceComponent
import com.example.characterslist.R
import com.example.characterslist.databinding.FragmentCharacterDetailBinding
import com.example.characterslist.detail.di.CharacterDetailModule
import com.example.characterslist.detail.di.DaggerCharacterDetailComponent
import com.example.commons.ui.base.BaseFragment
import com.example.commons.ui.extensions.observe
import com.google.android.material.snackbar.Snackbar
import com.vmadalin.commons.views.ProgressBarDialog
import javax.inject.Inject

class CharacterDetailFragment :
    BaseFragment<FragmentCharacterDetailBinding, CharacterDetailViewModel>(
        layoutId = R.layout.fragment_characters_detail
    ) {

    @Inject
    lateinit var progressDialog: ProgressBarDialog

    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        viewModel.loadCharacterDetail(args.characterId)
    }

    override fun onInitDependencyInjection() {
        DaggerCharacterDetailComponent
            .builder()
            .dataSourceComponent(dataSourceComponent(requireContext()))
            .characterDetailModule(CharacterDetailModule(this))
            .build()
            .inject(this)
    }

    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // ============================================================================================
    //  Private observers methods
    // ============================================================================================

    /**
     * Observer view state change on [CharacterDetailViewState].
     *
     * @param viewState State of character detail.
     */
    private fun onViewStateChange(viewState: CharacterDetailViewState) {
        when (viewState) {
            is CharacterDetailViewState.Loading ->
                progressDialog.show(R.string.character_detail_dialog_loading_text)
            is CharacterDetailViewState.Error ->
                progressDialog.dismissWithErrorMessage(R.string.character_detail_dialog_error_text)
            is CharacterDetailViewState.AddedToFavorite ->
                Snackbar.make(
                    requireView(),
                    R.string.character_detail_added_to_favorite_message,
                    Snackbar.LENGTH_LONG
                ).show()
            is CharacterDetailViewState.Dismiss ->
                findNavController().navigateUp()
            else -> progressDialog.dismiss()
        }
    }
}
