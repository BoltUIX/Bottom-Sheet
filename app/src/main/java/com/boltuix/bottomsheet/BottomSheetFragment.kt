package com.boltuix.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.boltuix.bottomsheet.databinding.BottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback

class BottomSheetFragment : Fragment() {

    private var _binding: BottomSheetBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = BottomSheetBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //......................................................
        val mBehavior = BottomSheetBehavior.from<View>(binding.bottomSheet)

        //Listening to state and slide changes
        mBehavior.addBottomSheetCallback(object : BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    mBehavior.isDraggable = false
                    binding.lytSheetHeader.visibility = View.GONE
                    binding.lytSheetHeaderWhite.visibility = View.VISIBLE
                } else {
                    mBehavior.isDraggable = true
                    binding.lytSheetHeader.visibility = View.VISIBLE
                    binding.lytSheetHeaderWhite.visibility = View.GONE
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
        binding.btExpand.setOnClickListener(View.OnClickListener {
            mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED)
        })
        //......................................................
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

/*Setting state
Standard and modal bottom sheets have the following states:

STATE_COLLAPSED: The bottom sheet is visible but only showing its peek height. This state is usually the 'resting position' of a bottom sheet, and should have enough height to indicate there is extra content for the user to interact with.
STATE_EXPANDED: The bottom sheet is visible at its maximum height and it is neither dragging or settling (see below).
STATE_HALF_EXPANDED: The bottom sheet is half-expanded (only applicable if behavior_fitToContents has been set to false), and is neither dragging or settling (see below).
STATE_HIDDEN: The bottom sheet is no longer visible and can only be re-shown programmatically.
STATE_DRAGGING: The user is actively dragging the bottom sheet up or down.
STATE_SETTLING: The bottom sheet is settling to specific height after a drag/swipe gesture. This will be the peek height, expanded height, or 0, in case the user action caused the bottom sheet to hide.*/