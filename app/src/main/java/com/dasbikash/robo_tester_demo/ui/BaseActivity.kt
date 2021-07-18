package com.dasbikash.robo_tester_demo.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dasBikash.app_crawler.AppCrawlerUtils
import com.google.android.material.bottomsheet.BottomSheetDialog

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCrawlerUtils
            .addRoboTestResultListener(
                this,this,{ testOutputDetails ->
                    BottomSheetDialog(this).apply {
                        setContentView(
                            TextView(this@BaseActivity).apply {
                                text = testOutputDetails.toString()
                            }
                        )
                    }.show()
                }
            )
    }
}