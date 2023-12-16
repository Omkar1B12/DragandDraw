package com.example.draganddraw

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

private const val TAG="BoxDrawingView"
class BoxDrawingView(context: Context,attrs:AttributeSet?=null): View(context,attrs) {
    override fun onDraw(canvas: Canvas) {
        canvas.drawPaint(backgroundPaint)

        boxen.forEach {
            box ->
            canvas.drawRect(box.left,box.top,box.right,box.bottom,boxPaint)
        }
    }

    private var currentBox:Box?=null
    private val boxen= mutableListOf<Box>()
    private val boxPaint= Paint().apply {
        color=0x22ff0000.toInt()
    }
    private val backgroundPaint=Paint().apply {
        color=0xfff8efe0.toInt()
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val current=PointF(event.x,event.y)
            var action=""

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                action="Action_DOWN"
                currentBox=Box(current).also {
                    boxen.add(it)
                }
            }
            MotionEvent.ACTION_MOVE->{
                action="Action_MOVE"
                updateCurrentBox(current)
            }
            MotionEvent.ACTION_UP->{
                action="Action_UP"
                updateCurrentBox(current)
                currentBox=null
            }
            MotionEvent.ACTION_CANCEL->{
                action="Action_CANCEL"
                currentBox=null
            }
        }
        Log.i(TAG,"$action at x=${current.x},y=${current.y}")
        return true
        }
    private fun updateCurrentBox(current:PointF){
        currentBox?.let {
            it.end=current
            invalidate()
        }
    }
    }

