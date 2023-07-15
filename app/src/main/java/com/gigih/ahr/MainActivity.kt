package com.gigih.ahr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.gigih.ahr.databinding.ActivityMainBinding
import com.gigih.ahr.util.gone
import com.gigih.ahr.util.setMargins
import com.gigih.ahr.util.visible
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {

   companion object {
       private const val TAG = "MainActivity"
   }

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomBehavior: BottomSheetBehavior<ConstraintLayout>

    private val bottomSheet get() =  binding.bsLatestDisaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomBehavior = BottomSheetBehavior.from(binding.bsLatestDisaster.root)

        initUi()
        initAction()
        bottomSheetConfiguration()

        binding.homeAppbar.setOnDisasterQueryChanged {
            Log.d(TAG, "onCreate: Query = $it")
        }

    }

    private fun initUi() {
        bottomBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

    private fun initAction() {
        binding.bsLatestDisaster.btnHideBs.setOnClickListener {
            bottomBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun bottomSheetConfiguration() {
        bottomBehavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> toggleBottomSheetLatestDisaster(true)
                    else -> toggleBottomSheetLatestDisaster(false)
                }
            }
            override fun onSlide(bottomSheet: View, slideOffset: Float) {}
        })
    }

    private fun toggleBottomSheetLatestDisaster(state: Boolean) {
        if (state) {
            val dimen32dp = resources.getDimension(R.dimen.dimen_32dp)
            bottomSheet.tvLabelLatestDisaster.setMargins(start = dimen32dp.toInt())
            bottomSheet.btnHideBs.visible()
            bottomSheet.btnHideBs.animate().alpha(1f)
        } else {
            bottomSheet.tvLabelLatestDisaster.setMargins(start = 0)
            bottomSheet.btnHideBs.gone()
            bottomSheet.btnHideBs.animate().alpha(0f)
        }
    }

}