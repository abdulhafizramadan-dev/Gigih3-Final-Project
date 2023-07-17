package com.gigih.ahr

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
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
    private var topWindowInset = 0

    private val dimen32dp get() =  resources.getDimension(R.dimen.dimen_32dp)
    private val dimen16dp get() =  resources.getDimension(R.dimen.dimen_16dp)
    private val dimen8dp get() =  resources.getDimension(R.dimen.dimen_8dp)

    private val bottomSheet get() =  binding.bsLatestDisaster

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomBehavior = BottomSheetBehavior.from(binding.bsLatestDisaster.root)

        setupWindowInset()
        initUi()
        initAction()
        bottomSheetConfiguration()

        binding.homeAppbar.setOnDisasterQueryChanged {
            Log.d(TAG, "onCreate: Query = $it")
        }

    }

    @Suppress("DEPRECATION")
    private fun setupWindowInset() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { _, insets ->
            topWindowInset = insets.systemWindowInsetTop
            binding.homeAppbar.setMargins(top = topWindowInset)
            WindowInsetsCompat.CONSUMED
        }
    }

    private fun initUi() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
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
            with(bottomSheet) {
                tvLabelLatestDisaster.setMargins(start = (dimen32dp + dimen16dp).toInt())
                btnHideBs.setMargins(start = dimen16dp.toInt(), top = dimen16dp.toInt())
                topIndicator.setMargins(top = dimen8dp.toInt())
                btnHideBs.visible()
                divider.visible()
                btnHideBs.animate().alpha(1f)
            }
        } else {
            with(bottomSheet) {
                tvLabelLatestDisaster.setMargins(start = dimen16dp.toInt(), top = dimen16dp.toInt())
                topIndicator.setMargins(top = 0)
                btnHideBs.gone()
                divider.gone()
                btnHideBs.animate().alpha(0f)
            }
        }
    }

}