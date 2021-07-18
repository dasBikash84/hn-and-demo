package com.dasbikash.robo_tester_demo.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dasbikash.robo_tester_demo.R
import com.dasbikash.robo_tester_demo.ui.fragments.view_models.BestNewsViewModel
import com.dasbikash.robo_tester_demo.ui.fragments.view_models.HackerNewsViewModel
import com.dasbikash.robo_tester_demo.ui.fragments.view_models.JobsNewsViewModel

class JobsStoriesFragment : BaseStoriesFragment() {
    override fun getHackerNewsViewModel(): HackerNewsViewModel
        = viewModels<JobsNewsViewModel>().value
}