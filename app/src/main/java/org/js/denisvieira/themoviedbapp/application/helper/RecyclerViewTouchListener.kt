package org.js.denisvieira.themoviedbapp.application.helper

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerTouchListener(context: Context, recyclerView: RecyclerView,
                            private var clickListener: SimpleOnClickListener?) : RecyclerView.OnItemTouchListener {

    private var gestureDetector: GestureDetector? = GestureDetector(context,
            object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            val child = recyclerView.findChildViewUnder(e.x, e.y)
            if (child != null && clickListener != null) {
                if (clickListener is ClickListener) {
                    val newClickListener = clickListener as ClickListener?
                    newClickListener!!.onLongClick(child, recyclerView.getChildAdapterPosition(child))
                }
            }
        }
    })

    override fun onTouchEvent(p0: RecyclerView, p1: MotionEvent) {
    }

    override fun onInterceptTouchEvent(recyclerView: RecyclerView, motionEvent: MotionEvent): Boolean {
        val child = recyclerView.findChildViewUnder(motionEvent.x, motionEvent.y)
        if (child != null && clickListener != null && gestureDetector!!.onTouchEvent(motionEvent)) {
            clickListener!!.onClick(child, recyclerView.getChildAdapterPosition(child))
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(p0: Boolean) {
    }

    interface ClickListener {
        fun onLongClick(view: View?, position: Int)
    }

    interface SimpleOnClickListener {
        fun onClick(view: View, position: Int)
    }


}