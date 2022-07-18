package com.burhanrashid52.photoediting

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition

class StickerBSFragment : BottomSheetDialogFragment() {
    private var mStickerListener: StickerListener? = null
    fun setStickerListener(stickerListener: StickerListener?) {
        mStickerListener = stickerListener
    }

    interface StickerListener {
        fun onStickerClick(bitmap: Bitmap?)
    }

    private val mBottomSheetBehaviorCallback: BottomSheetCallback = object : BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss()
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {}
    }

    @SuppressLint("RestrictedApi")
    override fun setupDialog(dialog: Dialog, style: Int) {
        super.setupDialog(dialog, style)
        val contentView = View.inflate(context, R.layout.fragment_bottom_sticker_emoji_dialog, null)
        dialog.setContentView(contentView)
        val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior
        if (behavior != null && behavior is BottomSheetBehavior<*>) {
            behavior.setBottomSheetCallback(mBottomSheetBehaviorCallback)
        }
        (contentView.parent as View).setBackgroundColor(resources.getColor(android.R.color.transparent))
        val rvEmoji: RecyclerView = contentView.findViewById(R.id.rvEmoji)
        val gridLayoutManager = GridLayoutManager(activity, 3)
        rvEmoji.layoutManager = gridLayoutManager
        val stickerAdapter = StickerAdapter()
        rvEmoji.adapter = stickerAdapter
        rvEmoji.setHasFixedSize(true)
        rvEmoji.setItemViewCacheSize(stickerPathList.size)
    }

    inner class StickerAdapter : RecyclerView.Adapter<StickerAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.row_sticker, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            // Load sticker image from remote url
            Glide.with(requireContext())
                    .asBitmap()
                    .load(stickerPathList[position])
                    .into(holder.imgSticker)
        }

        override fun getItemCount(): Int {
            return stickerPathList.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imgSticker: ImageView = itemView.findViewById(R.id.imgSticker)

            init {
                itemView.setOnClickListener {
                    if (mStickerListener != null) {
                        Glide.with(requireContext())
                                .asBitmap()
                                .load(stickerPathList[layoutPosition])
                                .into(object : CustomTarget<Bitmap?>(256, 256) {
                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                                        mStickerListener!!.onStickerClick(resource)
                                    }

                                    override fun onLoadCleared(placeholder: Drawable?) {}
                                })
                    }
                    dismiss()
                }
            }
        }
    }

    companion object {
        // Image Urls from flaticon(https://www.flaticon.com/stickers-pack/food-289)
        private val stickerPathList = arrayOf(
            R.drawable.s_21,
            R.drawable.s_22,
            R.drawable.s_23,
            R.drawable.s_24,
            R.drawable.s_25,
            R.drawable.s_26,
            R.drawable.s_27,
            R.drawable.s_28,
            R.drawable.s_29,
            R.drawable.s_30,
            R.drawable.s_31,
            R.drawable.s_32,
            R.drawable.s_33,
            R.drawable.s_34,
            R.drawable.s_35,
            R.drawable.s_36,
            R.drawable.s_37,
            R.drawable.s_38,
            R.drawable.s_39,
            R.drawable.s_40,
            R.drawable.s_41,
            R.drawable.s_42,
            R.drawable.s_43,
            R.drawable.s_44,
            R.drawable.s_45,
            R.drawable.s_46,
            R.drawable.s_47,
            R.drawable.s_48,
            R.drawable.s_49,
            R.drawable.s_50,
            R.drawable.s_51,
            R.drawable.s_52,
            R.drawable.s_53,
            R.drawable.s_54,
            R.drawable.s_55,
            R.drawable.s_56,
            R.drawable.s_57,
            R.drawable.s_58,
            R.drawable.s_59,
            R.drawable.s_60,
            R.drawable.s_61,
            R.drawable.s_62,
            R.drawable.s_63,
            R.drawable.s_64,
            R.drawable.s_65,
            R.drawable.s_66,
            R.drawable.s_67,
            R.drawable.s_68,
            R.drawable.s_69,
            R.drawable.s_70,
            R.drawable.s_71,
            R.drawable.s_72,
            R.drawable.s_73,
            R.drawable.s_74,
            R.drawable.s_75,
            R.drawable.absolutely_not,
            R.drawable.behind_you_skipper_1,
            R.drawable.behind_you_skipper_2

            /*    "https://cdn-icons-png.flaticon.com/256/4392/4392452.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392455.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392459.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392462.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392465.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392467.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392469.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392471.png",
                "https://cdn-icons-png.flaticon.com/256/4392/4392522.png",*/
        )
    }
}