package com.church.dgm

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.church.dgm.databinding.FragmentMyPlayerBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds


class MyPlayerFragment : Fragment() {
    lateinit var _bind: FragmentMyPlayerBinding
    val binding get() = _bind
    lateinit var mediaPlayer: MediaPlayer


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _bind = FragmentMyPlayerBinding.inflate(layoutInflater)

        MobileAds.initialize(requireContext(), getString(R.string.admob_app_id))
        val adRequest = AdRequest.Builder().build()
        binding.adView12.loadAd(adRequest)
        binding.adView22.loadAd(adRequest)


        Handler(Looper.myLooper()!!).postDelayed({
            binding.adView12.loadAd(adRequest)
            binding.adView22.loadAd(adRequest)
        }, 9000L)


        mediaPlayer = MediaPlayer()

        _bind.btnPlay.setOnClickListener {

            //Toast.makeText(requireContext(), "Hello world", Toast.LENGTH_LONG).show()
            _bind.loadingAudio.visibility = View.VISIBLE
            try {
                mediaPlayer.setDataSource("https://dgmfaithlife.radioca.st/stream")
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
                mediaPlayer.prepareAsync()

                mediaPlayer!!.setOnPreparedListener(MediaPlayer.OnPreparedListener { mp ->
                    mp.start()
                    mp.start()
                    it.visibility = View.GONE
                    _bind.loadingAudio.visibility = View.GONE
                    _bind.btnStop.visibility = View.VISIBLE
                })

            } catch (e: Exception) {
                Toast.makeText(requireContext(), "${e} Error", Toast.LENGTH_SHORT).show()
            }
        }
        _bind.btnStop.setOnClickListener {

            onDestroy()
            it.visibility = View.GONE
            _bind.loadingAudio.visibility = View.GONE
            _bind.btnPlay.visibility = View.VISIBLE
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar!!.show()
        (activity as AppCompatActivity).supportActionBar!!.title = "DGM Faith Life Mission"
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        super.onViewCreated(view, savedInstanceState)
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



    override fun onDestroy() {
        super.onDestroy()
        stop()
    }

    private fun stop() {
        mediaPlayer!!.pause()
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = MediaPlayer()
    }
}