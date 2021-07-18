package com.dasbikash.robo_tester_demo.ui.fragments.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.dasbikash.robo_tester_demo.data.remote.HackerNewsRepository
import com.dasbikash.robo_tester_demo.data.models.Story
import com.dasbikash.robo_tester_demo.data.models.StoryType
import com.dasbikash.robo_tester_demo.ui.LiveDataResource

abstract class HackerNewsViewModel(private val pageSize:Int = 7):ViewModel() {

    abstract fun getStoryType():StoryType
    private val storyIds = mutableListOf<Int>()
    private val stories = mutableListOf<Story>()
    private var currentStoryIdIndex:Int = 0

    private val repository by lazy { HackerNewsRepository() }

    private suspend fun fetchStoryIds(): Boolean =
        repository.getStoryIdsByType(getStoryType()).let {
            if (!it.isNullOrEmpty()){
                storyIds.clear()
                stories.clear()
                currentStoryIdIndex = 0
                storyIds.addAll(it.shuffled())
                true
            } else false
        }

    private suspend fun getStoryById(storyId: Int): Story? =
        repository.getStoryById(storyId)

    private suspend fun fetchNewStories():List<Story>?{
        if (storyIds.isEmpty()){
            if (!fetchStoryIds()) return null
        }

        if (currentStoryIdIndex >= storyIds.size - 1) return emptyList()

        val newStories = mutableListOf<Story>()

        for (i in 0 until pageSize) {
            currentStoryIdIndex += 1
            if (currentStoryIdIndex == storyIds.size) break
            val story = getStoryById(storyIds[currentStoryIdIndex])
            if (story==null) break
            else newStories.add(story)
        }
        return newStories
    }

    fun loadMoreStories():LiveData<LiveDataResource<List<Story>>>{
        return liveData{
            emit(LiveDataResource.loading<List<Story>>())
            fetchNewStories().let {
                if (it==null) emit(LiveDataResource.error<List<Story>>())
                else {
                    stories.addAll(it)
                    emit(LiveDataResource.success(stories.toList()))
                }
            }
        }
    }

}

