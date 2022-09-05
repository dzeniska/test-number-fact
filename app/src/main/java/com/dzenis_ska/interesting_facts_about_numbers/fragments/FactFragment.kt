package com.dzenis_ska.interesting_facts_about_numbers.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dzenis_ska.interesting_facts_about_numbers.R
import com.dzenis_ska.interesting_facts_about_numbers.databinding.FragmentFactBinding
import com.dzenis_ska.interesting_facts_about_numbers.utils.*

class FactFragment : Fragment(R.layout.fragment_fact), HasCustomTitle {

    private lateinit var binding: FragmentFactBinding
    private lateinit var fact: Fact

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fact = savedInstanceState?.getParcelable<Fact>(KEY_FACT) ?:
                arguments?.getParcelable(ARG_FACT) ?:
                throw IllegalArgumentException("You need to specify options to launch this fragment")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
//        initListView()
        initClick()
    }


    private fun initClick() = with(binding) {

    }

    private fun initUI() = with(binding) {
        tvNumber.text = fact.number.toString()
        tvFact.text = fact.numberFact
    }

    companion object {
        private const val ARG_FACT = "ARG_FACT"
        private const val KEY_FACT = "KEY_FACT"

        fun newInstance(fact: Fact): FactFragment {
            val args = bundleOf(ARG_FACT to fact)
            val fragment = FactFragment()
            fragment.arguments = args
            return fragment
        }

    }

    override fun getTitleRes(): Int = R.string.fact_f

}