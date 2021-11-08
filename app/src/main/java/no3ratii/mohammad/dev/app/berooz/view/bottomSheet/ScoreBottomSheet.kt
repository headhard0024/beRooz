package no3ratii.mohammad.dev.app.berooz.view.bottomSheet

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.score_bottom_sheet.*
import no3ratii.mohammad.dev.app.berooz.R
import no3ratii.mohammad.dev.app.berooz.base.listener.IBottomShotRespons


class ScoreBottomSheet : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.score_bottom_sheet, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //show phisical keybord in start bottomsheet
        val imgr: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        layOk.setOnClickListener {
            iBottomShotRespons.onEditTextValue("")
            dismiss()
        }

        layExit.setOnClickListener {
            dismiss()
        }
    }

    companion object {

        private lateinit var iBottomShotRespons: IBottomShotRespons
        fun setClicked(iBottomShotRespons: IBottomShotRespons) {
            Companion.iBottomShotRespons = iBottomShotRespons
        }

        fun newInstance(): ScoreBottomSheet {
            val frag = ScoreBottomSheet()
            val args = Bundle()
            frag.arguments = args
            return frag
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val bottomSheet = (requireView().parent as View)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            bottomSheet.backgroundTintMode = PorterDuff.Mode.CLEAR
            bottomSheet.backgroundTintList = ColorStateList.valueOf(Color.TRANSPARENT)
        }
        bottomSheet.setBackgroundColor(Color.TRANSPARENT)
    }
}
