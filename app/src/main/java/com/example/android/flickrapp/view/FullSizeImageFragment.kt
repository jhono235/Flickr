package com.example.android.flickrapp.view


import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.android.flickrapp.databinding.FragmentFirst2Binding
import com.example.android.flickrapp.model.StringConstants


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FullSizeImageFragment : Fragment() {

    private var _binding: FragmentFirst2Binding? = null






    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirst2Binding.inflate(inflater, container, false)







        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //Url to display the fullsize image
        val url = this.arguments?.getString(StringConstants.passedUrl).toString()

        if (!url.isNullOrBlank()) {

            Log.i("tag", "FromFragment: " + url)


            Glide.with(this)
                .load(url)
                .into(binding.ivFullsize)

        }







    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }






}