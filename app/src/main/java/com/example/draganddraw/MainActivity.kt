package com.example.draganddraw

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationSet
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit  var sceneView: View
    private lateinit var sunView:View
    private lateinit var SkyView:View
    private val blueSkyColor:Int by lazy {
        ContextCompat.getColor(this,R.color.blue_sky)
    }
    private val sunsetSkyColor:Int by lazy {
        ContextCompat.getColor(this,R.color.sunset_sky)
    }
    private val nightSkyColor:Int by lazy {
        ContextCompat.getColor(this,R.color.night_sky)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sceneView=findViewById(R.id.scene)
        sunView=findViewById(R.id.sun)
        SkyView=findViewById(R.id.sky)
        sceneView.setOnClickListener{
            startAnimation()
        }
    }
    private fun startAnimation(){
        val sunYStart=sunView.top.toFloat()
        val sunYEnd=SkyView.height.toFloat()

        val heightAnimator=ObjectAnimator
            .ofFloat(sunView,"y",sunYStart,sunYEnd)
            .setDuration(3600)
        heightAnimator.interpolator=AccelerateInterpolator()

        val sunsetSkyAnimator=ObjectAnimator
            .ofInt(SkyView,"backgroundColor",blueSkyColor,sunsetSkyColor)
            .setDuration(3000)
        sunsetSkyAnimator.setEvaluator(ArgbEvaluator())

        val nightSkyAnimator=ObjectAnimator.ofInt(SkyView,"backgroundColor",sunsetSkyColor,nightSkyColor)
            .setDuration(1500)
        nightSkyAnimator.setEvaluator(ArgbEvaluator())

        val animationSet= AnimatorSet()
        animationSet.play(heightAnimator)
            .with(sunsetSkyAnimator)
            .before(nightSkyAnimator)
        animationSet.start()




    }
}