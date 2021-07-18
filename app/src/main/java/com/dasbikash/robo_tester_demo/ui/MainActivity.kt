package com.dasbikash.robo_tester_demo.ui

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.dasBikash.app_crawler.AppCrawlerUtils
import com.dasBikash.app_crawler_model.TestSettings
import com.dasbikash.robo_tester_demo.R
import com.dasbikash.robo_tester_demo.ui.utils.roboLog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpNavGraph()

        lifecycleScope.launchWhenResumed {
            delay(2000)
            AppCrawlerUtils.startTest(
                this@MainActivity,
                testSettings = TestSettings(
                    maxRunTimeMinutes = 1,
                    testScriptPaths = null
                )
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        roboLog("onDestroy")
    }

    private fun setUpNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.nav_graph)
        navController.graph = graph

        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemReselectedListener {}
    }
}