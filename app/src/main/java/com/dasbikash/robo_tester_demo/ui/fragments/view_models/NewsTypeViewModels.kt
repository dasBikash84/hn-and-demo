package com.dasbikash.robo_tester_demo.ui.fragments.view_models

import com.dasbikash.robo_tester_demo.data.models.StoryType

class NewNewsViewModel:HackerNewsViewModel(){
    override fun getStoryType(): StoryType = StoryType.New
}

class BestNewsViewModel:HackerNewsViewModel(){
    override fun getStoryType(): StoryType = StoryType.Best
}
class TopNewsViewModel:HackerNewsViewModel(){
    override fun getStoryType(): StoryType = StoryType.Top
}
class JobsNewsViewModel:HackerNewsViewModel(){
    override fun getStoryType(): StoryType = StoryType.Jobs
}
class ShowsNewsViewModel:HackerNewsViewModel(){
    override fun getStoryType(): StoryType = StoryType.Shows
}