package com.church.dgm

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.church.dgm.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    lateinit var _bind: FragmentSplashBinding
    val bind get() = _bind

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind = FragmentSplashBinding.inflate(layoutInflater)

        findNavController().navigate(R.id.action_splashFragment_to_myPlayerFragment)

        return bind!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(false)
        (activity as AppCompatActivity).supportActionBar!!.hide()
        super.onViewCreated(view, savedInstanceState)
    }

}