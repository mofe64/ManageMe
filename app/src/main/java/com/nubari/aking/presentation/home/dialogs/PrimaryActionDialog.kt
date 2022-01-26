package com.nubari.aking.presentation.home.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nubari.aking.R
import com.nubari.aking.databinding.PrimaryActionDialogBinding

class PrimaryActionDialog : BottomSheetDialogFragment(), View.OnClickListener {

    private var _binding: PrimaryActionDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PrimaryActionDialogBinding.inflate(
            inflater, container, false
        )
        binding.createTask.setOnClickListener(this)
        binding.modalCancelBtn.setOnClickListener(this)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        view?.let {
            when (view.id) {
                R.id.create_task -> {
                    val navHost =
                        activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment_content_main)
                    val navController = navHost?.findNavController()
                    val action =
                        PrimaryActionDialogDirections.actionPrimaryActionDialogToAddEditTask()
                    navController?.navigate(action)
                }
                R.id.modal_cancel_btn -> {
                    dismiss()
                }
                else -> {}
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = PrimaryActionDialog()
    }
}