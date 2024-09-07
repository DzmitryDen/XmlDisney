package com.hfad.xmldisney.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.hfad.xmldisney.R
import com.hfad.xmldisney.databinding.BannerViewBinding

class BannerView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    private val binding: BannerViewBinding

    init {
        binding = BannerViewBinding.inflate(LayoutInflater.from(context), this, true)
        context.theme.obtainStyledAttributes(attrs, R.styleable.BannerView, 0, 0).run {
            val closeIcon = getDrawable(R.styleable.BannerView_closeIcon)
            binding.close.setImageDrawable(closeIcon)
            val background = getColor(R.styleable.BannerView_backgroundColor, Color.TRANSPARENT)
            binding.bannerContainer.setBackgroundColor(background)
            val icon = getDrawable(R.styleable.BannerView_bannerIcon)
            binding.icon.setImageDrawable(icon)
            val text = getText(R.styleable.BannerView_bannerText)
            binding.message.text = text
        }
    }

    fun setBackground(color: Int) {
        binding.bannerContainer.setBackgroundColor(color)
    }

    fun setIcon(icon: Drawable?) {
        binding.icon.setImageDrawable(icon)
    }

    fun setCloseIcon(icon: Drawable) {
        binding.close.setImageDrawable(icon)
    }

    fun setMessage(message: String) {
        binding.message.text = message
    }

    fun close(onClick: () -> Unit) {
        binding.close.setOnClickListener {
            onClick.invoke()
        }
    }
}