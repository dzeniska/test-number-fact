package com.dzenis_ska.interesting_facts_about_numbers.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.dzenis_ska.interesting_facts_about_numbers.R
import com.dzenis_ska.interesting_facts_about_numbers.databinding.ActivityMainBinding
import com.dzenis_ska.interesting_facts_about_numbers.fragments.Fact
import com.dzenis_ska.interesting_facts_about_numbers.fragments.FactFragment
import com.dzenis_ska.interesting_facts_about_numbers.fragments.MainFragment
import com.dzenis_ska.interesting_facts_about_numbers.utils.HasCustomTitle
import com.dzenis_ska.interesting_facts_about_numbers.utils.Navigator
import com.dzenis_ska.interesting_facts_about_numbers.utils.hide
import com.dzenis_ska.interesting_facts_about_numbers.utils.show

class MainActivity : AppCompatActivity(), Navigator {

    private lateinit var binding: ActivityMainBinding

    private val currentFragment: Fragment
        get() = supportFragmentManager.findFragmentById(R.id.fragmentContainer)!!

    private val fragmentListener = object : FragmentManager.FragmentLifecycleCallbacks() {
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            super.onFragmentViewCreated(fm, f, v, savedInstanceState)
            updateUi()
        }
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(root) }
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainer, MainFragment())
                .commit()
        }
        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentListener, false)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        updateUi()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentListener)
    }

    private fun updateUi() {
        val fragment = currentFragment
        binding.toolbar.menu.clear()
        binding.toolbar.title = ""
        Log.d("!!!updateUi", "${fragment}")

        if (fragment is HasCustomTitle) {
            binding.toolbar.show()
            binding.toolbar.title = getString(fragment.getTitleRes())
        } else {
            binding.toolbar.hide()
        }

        if (supportFragmentManager.backStackEntryCount > 0) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//            supportActionBar?.setDisplayShowHomeEnabled(true)
        } else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
//            supportActionBar?.setDisplayShowHomeEnabled(false)
        }

//        if (fragment is HasCustomAction) {
//            createCustomToolbarAction(fragment.getCustomAction())
//        } else {
//            binding.toolbar.menu.clear()
//        }
    }

    override fun goToFactFragment(fact: Fact) {
        launchFragment(FactFragment.newInstance(fact))
    }

    private fun launchFragment(fragment: Fragment) {
        Log.d("!!!launchFragment", "${fragment}")

        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragmentContainer, fragment)
            .commit()

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}