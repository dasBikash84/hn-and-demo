package com.dasbikash.robo_tester_demo.ui.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.dasbikash.robo_tester_demo.R
import com.dasbikash.robo_tester_demo.ui.LiveDataResource
import com.dasbikash.robo_tester_demo.ui.StoryWebViewActivity
import com.dasbikash.robo_tester_demo.ui.fragments.view_models.HackerNewsViewModel
import com.dasbikash.robo_tester_demo.ui.utils.StoryAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_base_stories.*
import java.util.*

abstract class BaseStoriesFragment : Fragment() {

    private val TAG by lazy { this.javaClass.simpleName }

    abstract fun getHackerNewsViewModel(): HackerNewsViewModel

    private val storyAdapterView by lazy {
        StoryAdapter({ url ->
            StoryWebViewActivity.getIntent(requireContext(), url)?.let { startActivity(it) }
        }, {
            Logger.d("userId: $it")
        }, {
            lifecycleScope.launchWhenResumed {
                loadMoreNews()
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_base_stories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadMoreNews()
        rvStories.adapter = storyAdapterView
        tvPageTitle.text =
            getHackerNewsViewModel().getStoryType().typeId
                .replace("stories"," Stories")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        fab.setOnClickListener {
            rvStories.scrollBy(0,resources.displayMetrics.heightPixels/2)
        }
    }

    private fun loadMoreNews() {
        getHackerNewsViewModel().loadMoreStories().observe(viewLifecycleOwner) {
            when (it?.status) {
                LiveDataResource.Status.LOADING -> {
                    log("Loading ${getHackerNewsViewModel().getStoryType().typeId} stories!!")
                    progressBar.isVisible = true
                }
                LiveDataResource.Status.SUCCESS -> {
                    it.data?.asSequence()?.forEach { story ->
                        println(story.toString())
                    }
                    val count = it.data?.size ?: 0
                    log("$count ${getHackerNewsViewModel().getStoryType().typeId} stories loaded ")
                    if (count > 0) {
                        storyAdapterView.submitList(it.data)
                    }
                    progressBar.isVisible = false
                }
                LiveDataResource.Status.ERROR -> {
                    log("Error in loading ${getHackerNewsViewModel().getStoryType().typeId} stories!!!")
                    progressBar.isVisible = false
                }
                else -> {
                }
            }
        }
    }

    private fun log(message: String) {
        Logger.d("$TAG : $message")
//        context?.showToast(message)
    }
}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(this, message, duration).show()
    }
}