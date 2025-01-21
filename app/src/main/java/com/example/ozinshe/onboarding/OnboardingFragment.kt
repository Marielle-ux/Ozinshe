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
                    binding.btnSkipOnboardingFragment.visibility = View.INVISIBLE
                    binding.btnNextOnboardingFragment.visibility = View.VISIBLE
                } else {
                    binding.btnSkipOnboardingFragment.visibility = View.VISIBLE
                    binding.btnNextOnboardingFragment.visibility = View.INVISIBLE
                }
            }
        }
        binding.dotsIndicator.setViewPager2(binding.ViewPager2OnboardingFragment)
        binding.ViewPager2OnboardingFragment.registerOnPageChangeCallback(viewPagerCallback)

        binding.btnSkipOnboardingFragment.setOnClickListener {
            val lastPageIndex = OnboardingInfoList.onboardingInfoList.size - 1
            binding.ViewPager2OnboardingFragment.setCurrentItem(lastPageIndex, true)
        }
        binding.ViewPager2OnboardingFragment.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == OnboardingInfoList.onboardingInfoList.size - 1) {
                    binding.btnSkipOnboardingFragment.visibility = View.GONE
                } else {
                    binding.btnSkipOnboardingFragment.visibility = View.VISIBLE
                }
            }
        })


        binding.ViewPager2OnboardingFragment.registerOnPageChangeCallback(viewPagerCallback)

        binding.btnNextOnboardingFragment.setOnClickListener {
            val currentItem = binding.ViewPager2OnboardingFragment.currentItem
            val lastPageIndex = OnboardingInfoList.onboardingInfoList.size - 1

            if (currentItem < lastPageIndex) {
                binding.ViewPager2OnboardingFragment.setCurrentItem(currentItem + 1, true)
            } else {
                findNavController().navigate(R.id.action_onboardingFragment_to_loginFragment)
            }
        }

    }
}

