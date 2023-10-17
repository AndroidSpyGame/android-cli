package ru.kpfu.itis.spymasters.tools

import android.graphics.drawable.AnimationDrawable

object BackgroundAnimator {

    private const val DEFAULT_ENTER_FADE_DURATION = 10
    private const val DEFAULT_EXIT_FADE_DURATION = 4000

    fun animate(
        animationDrawable: AnimationDrawable,
        enterFadeDuration: Int = DEFAULT_ENTER_FADE_DURATION,
        exitFadeDuration: Int = DEFAULT_EXIT_FADE_DURATION
    ) {
        animationDrawable.setEnterFadeDuration(enterFadeDuration)
        animationDrawable.setExitFadeDuration(exitFadeDuration)
        animationDrawable.start()
    }
}