package com.musaceae.tsplashscreen

import android.animation.ValueAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*
import android.animation.ObjectAnimator



class SplashActivity : AppCompatActivity() {
    private var mDelayHandler:Handler? = null
    private var SPLASH_DELAY:Long = 30000

    internal val mRunnable:Runnable = Runnable {
        if(!isFinishing){
            val intent=Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        mDelayHandler = Handler()

        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        ValueAnimator.ofFloat(600f, 300f).apply {
            duration = 1000
            addUpdateListener {
                tiger.y = it.animatedValue as Float
            }
            start()
        }

        ValueAnimator.ofFloat(-180f, 0f).apply {
            duration = 1000
            addUpdateListener {
                tiger.rotation = it.animatedValue as Float
            }
            start()
        }

        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 1000
            addUpdateListener {
                tiger.alpha = it.animatedValue as Float
            }
            start()
        }

        tiger.scaleX = 0.2f
        tiger.scaleY = 0.2f
        ValueAnimator.ofFloat(0.2f, 1f).apply {
            duration = 1000
            startDelay = 1000
            addUpdateListener {
                tiger.scaleX = it.animatedValue as Float
                tiger.scaleY = it.animatedValue as Float
            }
            start()
        }

    }

    public override fun onDestroy(){
        if(mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }




}
