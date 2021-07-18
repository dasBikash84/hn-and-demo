package com.dasbikash.robo_tester_demo.ui.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dasbikash.robo_tester_demo.R
import com.dasbikash.robo_tester_demo.data.models.Story
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.view_story_preview.view.*
import java.util.*

class StoryAdapter(
    val viewDetailsStory:(String)->Unit,
    val browseUser:(String)->Unit,
    val onLastItemLoad:()->Unit
):
    ListAdapter<Story, StoryAdapter.ViewHolder>(DiffUtilCallBack()) {

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        fun bind(story: Story){
            itemView.tvStoryTitle.text = story.title ?: ""
            itemView.tvDate.text = story.time?.let { Date(it*1000L).toString() } ?: ""
            itemView.tvStoryTitle.isVisible = !story.title.isNullOrBlank()
            itemView.tvDate.isVisible = story.time != null
            itemView.btnViewStory.isVisible = !story.url.isNullOrBlank()
//            itemView.btnViewUser.isVisible = !story.userId.isNullOrBlank()
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(
            oldItem: Story,
            newItem: Story
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Story,
            newItem: Story
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(
                    R.layout.view_story_preview,parent,false
                )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position).let { story ->
            holder.bind(story)
            story.url?.let { url ->
                holder.itemView.btnViewStory.setOnClickListener {
                    viewDetailsStory(url)
                }
            }
            story.userId?.let { userId ->
                holder.itemView.btnViewUser.setOnClickListener { it ->
                    browseUser(userId)
                }
            }
        }
        if (position == itemCount-1){
            onLastItemLoad()
        }
    }
}


fun roboLog(message:String){ Logger.d("RoboTester: $message") }
fun roboLog(data:Any){ Logger.d("RoboTester: ${data.toString()}") }
