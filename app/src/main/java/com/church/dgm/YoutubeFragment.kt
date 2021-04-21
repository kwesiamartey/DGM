package com.church.dgm

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import androidx.fragment.app.Fragment
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.church.dgm.databinding.FragmentWebsiteBinding
import com.church.dgm.databinding.FragmentYoutubeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class YoutubeFragment : Fragment() {


    lateinit var _bind :FragmentYoutubeBinding
    val view get() = _bind

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind =  FragmentYoutubeBinding.inflate(layoutInflater)

        MobileAds.initialize(requireContext(), getString(R.string.admob_app_id))
        val adRequest = AdRequest.Builder().build()
        view.adViewsWeb.loadAd(adRequest)
        view.adViewsWeb1.loadAd(adRequest)
        view.adViewsWeb3.loadAd(adRequest)

        MobileAds.initialize(requireContext(), getString(R.string.admob_app_id))
        val adRequests = AdRequest.Builder().build()
        view.adViewsWebview.loadAd(adRequests)

        Handler(Looper.myLooper()!!).postDelayed({
            view.adViewsWeb.loadAd(adRequest)
            view.adViewsWebview.loadAd(adRequests)
            view.adViewsWeb1.loadAd(adRequest)
            view.adViewsWeb3.loadAd(adRequest)
        }, 9000L)

        val mWebView = view.wbView as WebView
        // Enable Javascript

        // Enable Javascript
        val webSettings: WebSettings = mWebView.getSettings()
        webSettings.javaScriptEnabled = true
        webSettings.setJavaScriptEnabled(true)
        webSettings.setBuiltInZoomControls(false)
        webSettings.setSupportZoom(false)
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true)
        webSettings.setAllowFileAccess(true)
        webSettings.setDomStorageEnabled(true)
        mWebView.loadUrl("https://www.youtube.com/channel/UCx4web1ORIvDIik5mB_Dmfw/videos")        // Force links and redirects to open in the WebView instead of in a browser

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(WebViewClient())
        val progress = ProgressDialog.show(
            requireActivity(),
            "DGM Faith Life Mission...",
            "Please wait for it to finish, Streaming depends on the speed of your internet....",
            true
        )
        mWebView.setWebViewClient(object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                if (progress != null) progress.dismiss()
            }
        })




        return view.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "DGM FAith Life Temple"
        super.onCreate(savedInstanceState)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.player -> {
                findNavController().navigate(R.id.myPlayerFragment)
            }
            R.id.website -> {
                findNavController().navigate(R.id.websiteFragment)
            }
            R.id.youtube -> {
                findNavController().navigate(R.id.youtubeFragment)
            }

        }
        return super.onOptionsItemSelected(item)
    }
}