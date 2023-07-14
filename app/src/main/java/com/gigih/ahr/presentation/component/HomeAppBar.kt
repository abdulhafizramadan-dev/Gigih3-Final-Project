package com.gigih.ahr.presentation.component

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import com.gigih.ahr.databinding.LayoutHomeAppbarBinding
import com.gigih.ahr.util.layoutInflater

class HomeAppBar(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val binding: LayoutHomeAppbarBinding by lazy {
        LayoutHomeAppbarBinding.inflate(context.layoutInflater, this)
    }

    fun setOnDisasterQueryChanged(onChanged: (String) -> Unit) {
        binding.tilDisaster.editText?.doOnTextChanged { text, _, _, _ ->
            onChanged(text.toString())
        }
    }

}