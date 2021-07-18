package com.dasbikash.robo_tester_demo.ui.fragments

import androidx.fragment.app.viewModels
import com.dasbikash.robo_tester_demo.ui.fragments.view_models.HackerNewsViewModel
import com.dasbikash.robo_tester_demo.ui.fragments.view_models.NewNewsViewModel

class NewStoriesFragment : BaseStoriesFragment() {
    override fun getHackerNewsViewModel(): HackerNewsViewModel
        = viewModels<NewNewsViewModel>().value
}