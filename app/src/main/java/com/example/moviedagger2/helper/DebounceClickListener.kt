package com.example.moviedagger2.helper

import android.os.SystemClock
import android.view.View
import java.util.*

abstract class DebounceClickListener(private val timeInMillis:Long = 1000 ) : View.OnClickListener {

    private val myMap:  MutableMap<View, Long>

    abstract fun onDebounce(v: View)

    init {
        this.myMap = WeakHashMap<View, Long>()
    }

    override fun onClick(clickedView: View) {
        val previousClickTimeStamp =  myMap[clickedView]
        val currentTimeStamp = SystemClock.uptimeMillis()

        myMap[clickedView] = currentTimeStamp

        if (previousClickTimeStamp == null || Math.abs(currentTimeStamp - previousClickTimeStamp.toLong()) > timeInMillis){
            onDebounce(clickedView)
        }
    }
}