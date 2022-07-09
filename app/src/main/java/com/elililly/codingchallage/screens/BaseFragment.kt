package com.elililly.codingchallage.screens

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    fun replaceFragment(
        newFragment: BaseFragment,
        newFragmentTag: String, isReplaceWithBackStack: Boolean
    ) {
        if (activity?.isFinishing == false) {

            val transaction = activity?.supportFragmentManager?.beginTransaction()
            transaction?.add(id, newFragment, newFragmentTag)
            if (isReplaceWithBackStack) {
                transaction?.addToBackStack(newFragmentTag)
            }
            transaction?.commitAllowingStateLoss()
        }
    }
}