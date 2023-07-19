package com.gigih.ahr.presentation.component

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.gigih.ahr.R
import com.gigih.ahr.databinding.LayoutSettingItemBinding
import com.gigih.ahr.util.layoutInflater

class SettingViewItem(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    private val binding: LayoutSettingItemBinding by lazy {
        LayoutSettingItemBinding.inflate(context.layoutInflater, this)
    }

    private var title: String = ""
    private var subtitle: String = ""

    private val attributes: TypedArray by lazy {
        context.obtainStyledAttributes(attrs, R.styleable.SettingViewItem, 0, 0)
    }

    init {
        initAttributes()
        initUi()
    }

    private fun initAttributes() {
        title = attributes.getString(R.styleable.SettingViewItem_title) ?: ""
        subtitle = attributes.getString(R.styleable.SettingViewItem_subtitle) ?: ""
    }

    private fun initUi() {
        with(binding) {
            tvTitle.text = title
            tvSubtitle.text = subtitle
        }
    }

}