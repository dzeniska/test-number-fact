package com.dzenis_ska.interesting_facts_about_numbers.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.SimpleAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.dzenis_ska.interesting_facts_about_numbers.R
import com.dzenis_ska.interesting_facts_about_numbers.databinding.FragmentMainBinding
import com.dzenis_ska.interesting_facts_about_numbers.utils.*

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainFragmentViewModel by activityViewModels { factory() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initClick()
        initVM()
    }

    private fun initVM() {
        viewModel.state.observe(viewLifecycleOwner) {
            Log.d("!!!factsFromHistory", "${it}")

            when (it) {
                StateUI.NO_LOAD -> cancelLoadInitUI()
                StateUI.LOADING -> loadNumberFactInitUI()
                StateUI.LOADING_FROM_DB -> loadHistoryFactsFromDB()
            }
        }
        viewModel.factOfNumber.observe(viewLifecycleOwner) { event->
            event.getValue()?.let{ fact->
                if (fact == Fact.DEFAULT) toast("Something went wrong")
                else navigator().goToFactFragment(fact)
            }
        }
        viewModel.factsFromHistory.observe(viewLifecycleOwner) { listReverse ->
            Log.d("!!!factsFromHistory", "${listReverse}")
            val list = listReverse.reversed()
            val adapter = SimpleAdapter(
                context, list.map {
                    mapOf(
                        KEY_NUMBER_FACT to it.numberFact
                    )
                }, R.layout.list_item,
                arrayOf(KEY_NUMBER_FACT),
                intArrayOf(R.id.textView)
            )
            binding.listView.adapter = adapter
            binding.listView.onItemClickListener = AdapterView.OnItemClickListener{_,_,position,_->
                val selectedItem = list[position]
                navigator().goToFactFragment(selectedItem)
            }

        }
    }

    private fun initClick() = with(binding) {
        etEnterNumber.setOnFocusChangeListener { v, hasFocus ->
            Log.d("!!!initClick", "${hasFocus}")
            if (hasFocus) listView.hide() else listView.show()
        }

        etEnterNumber.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                getFact()
            }
            return@setOnEditorActionListener false
        }


        ibGetFact.setOnClickListener {
            hideSoftKeyboards()
            getFact()
        }

        ibGetFactAboutRandomNumber.setOnClickListener {
            if (!CheckNetwork.isNetworkAvailable(context)) return@setOnClickListener
            viewModel.getFactOfRandomNumber()
        }

    }

    private fun loadNumberFactInitUI() = with(binding){
        etEnterNumber.hide()
        progressBar.show()
    }

    private fun loadHistoryFactsFromDB() = with(binding){
        progressBarLoadHistoryList.show()
    }

    private fun cancelLoadInitUI() = with(binding){
        etEnterNumber.show()
        progressBar.hide()
        progressBarLoadHistoryList.hide()
    }

    private fun getFact() = with(binding){
        if (etEnterNumber.text?.isEmpty() == true) {
            toast("You need to enter some number")
        } else {
            if (CheckNetwork.isNetworkAvailable(context)) {
                viewModel.getFactOfNumber(etEnterNumber.text.toString())
            }
        }
    }

    private fun initUI() = with(binding) {
    }

    companion object {
        const val KEY_NUMBER_FACT = "KEY_NUMBER_FACT"
    }

}