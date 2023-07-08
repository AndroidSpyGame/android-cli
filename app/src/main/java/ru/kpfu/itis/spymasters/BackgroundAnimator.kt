package ru.kpfu.itis.spymasters

import android.graphics.drawable.AnimationDrawable

object BackgroundAnimator {
    fun animate(
        animationDrawable: AnimationDrawable,
        enterFadeDuration: Int,
        exitFadeDuration: Int) {
        animationDrawable.setEnterFadeDuration(enterFadeDuration)
        animationDrawable.setExitFadeDuration(exitFadeDuration)
        animationDrawable.start()
    }
}