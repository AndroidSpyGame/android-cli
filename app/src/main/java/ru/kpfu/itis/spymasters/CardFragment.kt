package ru.kpfu.itis.spymasters

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import ru.kpfu.itis.spymasters.databinding.FragmentCardBinding


class CardFragment : Fragment(R.layout.fragment_card) {
    private var buttonNext: Button? = null
    private var buttonStart: Button? = null

    private var binding: FragmentCardBinding? = null

//    private var stateUi: State  = State.CloseCard
    private val listPlaces: List<String> = PlaceRepository.list
    private val listPlayers: List<String> = PlayersRepository.list
    private val place: String = listPlaces[(0..listPlaces.size).random()]

    var counter = 0
    var spyNumber = (0..listPlayers.size-1).random()

    private var cardNewClose: CardView? = null
    private var cardNewOpenSpy: CardView? = null
    private var cardNewOpenSimplePlayer: CardView? = null
    private var cardCurrent: CardView? = null

    private var isBackOpened: Boolean = true


    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        BackgroundAnimator.animate(binding?. as AnimationDrawable, 10, 4000)
//        BackgroundAnimator.animate(R.layout.fragment_card as AnimationDrawable, 10,4000)

        binding = FragmentCardBinding.bind(view)

        buttonNext = binding?.btnNext
        buttonStart = binding?.btnStart
        binding?.tvCardBackOneOfCategory?.text = place
        binding?.tvCardPlayer?.text = listPlayers[counter]

        cardNewClose = binding?.cvClosedCard
        cardNewOpenSpy = binding?.cvSpy
        cardNewOpenSimplePlayer = binding?.cvSimplePlayer


        cardNewClose?.setOnClickListener {
            if (spyNumber == counter) {
                if (isBackOpened) {
                    cardCurrent = flipCard(this.context, cardNewClose, cardNewOpenSpy)
                    showBtnNext()
                } else {
                    cardCurrent = flipCard(this.context, cardNewOpenSpy, cardNewClose)
                }
            } else {
                if (isBackOpened) {
                    showBtnNext()
                    cardCurrent = flipCard(this.context, cardNewClose, cardNewOpenSimplePlayer)
                } else {
                    cardCurrent = flipCard(this.context, cardNewOpenSimplePlayer, cardNewClose)
                }
            }
        }

        buttonNext?.setOnClickListener {
            if (counter == listPlayers.size - 1) {
                buttonNext?.visibility = View.GONE
            } else {
                if(!isBackOpened) {
                    cardCurrent = flipCard(this.context, cardCurrent, cardNewClose)
                }
            }
            counter++
            buttonNext?.visibility = View.GONE
            binding?.tvCardPlayer?.text = listPlayers[counter]
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("ResourceType")
    fun showBtnNext() {
        if (counter == listPlayers.size - 1) {
            buttonNext?.visibility = View.GONE
            buttonStart?.visibility = View.VISIBLE
        } else {
            buttonNext?.visibility = View.VISIBLE // кнопка становится видимой
        }
    }

    fun flipCard(context: Context?, closedCard: CardView?, openedCard: CardView?): CardView? {
        try {
            closedCard?.visibility = View.VISIBLE
            val flipOutAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_out
                ) as AnimatorSet
            flipOutAnimatorSet.setTarget(closedCard)
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            flipOutAnimatorSet.start()

            flipInAnimatorSet.setTarget(openedCard)
            flipInAnimatorSet.start()
            openedCard?.visibility = View.VISIBLE

            flipInAnimatorSet.doOnEnd {
            }
            isBackOpened = !isBackOpened
        } catch (e: Exception) {
            Exception(e)
        }
        return openedCard
    }

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
}