package com.example.ozinshe.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.ozinshe.R
import com.example.ozinshe.data.OnboardingInfoList
import com.example.ozinshe.databinding.FragmentOnboardingBinding

class OnboardingFragment : Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = OnboardingAdapter()
        adapter.submitList(OnboardingInfoList.onboardingInfoList)
        binding.ViewPager2OnboardingFragment.adapter = adapter

        val viewPagerCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == OnboardingInfoList.onboardingInfoList.size - 1) {
                    binding.btnSkipOnboardingFragment.visibility = View.VISIBLE
                    binding.btnNextOnboardingFragment.visibility = View.INVISIBLE
                } else {

                    binding.btnSkipOnboardingFragment.visibility = View.INVISIBLE
                    binding.btnNextOnboardingFragment.visibility = View.VISIBLE
                }
            }
        }

        binding.dotsIndicator.setViewPager2(binding.ViewPager2OnboardingFragment)
        binding.ViewPager2OnboardingFragment.registerOnPageChangeCallback(viewPagerCallback)

        binding.btnSkipOnboardingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
        }
    }
}
