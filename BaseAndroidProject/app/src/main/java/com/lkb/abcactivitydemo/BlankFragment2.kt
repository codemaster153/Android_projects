package com.lkb.abcactivitydemo

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
/**
 * A simple [Fragment] subclass.
 * Use the [BlankFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BlankFragment2 : Fragment() {
    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
        Log.d("LKB-F-2", "onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LKB-F-2", "onCreateView()")
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LKB-F-2", "onStart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LKB-F-2", "onDestroy()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("LKB-F-2", "onDestroyView()")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("LKB-F-2", "onAttach()")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("LKB-F-2", "onActivityCreated()")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        Log.d("LKB-F-2", "onAttachFragment()")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LKB-F-2", "onDetach()")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LKB-F-2", "onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LKB-F-2", "onStop()")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LKB-F-2", "onResume()")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, param2: String?): BlankFragment2 {
            val fragment = BlankFragment2()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}