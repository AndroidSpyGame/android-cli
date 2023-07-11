package ru.kpfu.itis.spymasters

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.kpfu.itis.spymasters.databinding.FragmentCardBinding


@Suppress("DEPRECATION")
class CardFragment : Fragment(R.layout.fragment_card) {
    private var buttonNext: Button? = null
    private var buttonStart: Button? = null

    private var binding: FragmentCardBinding? = null

    private val listPlaces: List<String> = PlaceRepository.list
    private val place: String = listPlaces[(0..listPlaces.size).random()]

    var counter = 0

    private var cardNewClose: CardView? = null

    private var cardNewOpenSpy: CardView? = null
    private var cardNewOpenSimplePlayer: CardView? = null
    private var cardCurrent: CardView? = null
    private var isBackOpened: Boolean = true

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCardBinding.bind(view)

        // получение из бандла списка игроков
        val players: ArrayList<Player>? = arguments?.getParcelableArrayList("players")
        val timer: Int? = arguments?.getInt("timer")
        val countSpy: Int? = arguments?.getInt("countSpy")

        BackgroundAnimator.animate(binding?.root?.background as AnimationDrawable, 10, 4000)

        buttonNext = binding?.btnNext
        buttonStart = binding?.btnStart
        binding?.tvCardBackOneOfCategory?.text = place

        binding?.tvCardPlayer?.text = players?.get(counter)?.name

        cardNewClose = binding?.cvClosedCard
        cardNewOpenSpy = binding?.cvSpy
        cardNewOpenSimplePlayer = binding?.cvSimplePlayer


        cardNewClose?.setOnClickListener {
            if (players?.get(counter)?.isSpy == true) {
                if (isBackOpened) {
                    cardCurrent = flipCard(this.context, cardNewClose, cardNewOpenSpy)
                    showBtnNext(players)
                } else {
                    cardCurrent = flipCard(this.context, cardNewOpenSpy, cardNewClose)
                }
            } else {
                if (isBackOpened) {
                    showBtnNext(players)
                    cardCurrent = flipCard(this.context, cardNewClose, cardNewOpenSimplePlayer)
                } else {
                    cardCurrent = flipCard(this.context, cardNewOpenSimplePlayer, cardNewClose)
                }
            }
        }

        buttonNext?.setOnClickListener {
            if (counter == (players?.size?.minus(1) ?: Int)) { // чекнуть на корректность
                buttonNext?.visibility = View.GONE
            } else {
                if(!isBackOpened) {
                    cardCurrent = flipCard(this.context, cardCurrent, cardNewClose)
                }
            }
            counter++
            buttonNext?.visibility = View.GONE
            binding?.tvCardPlayer?.text = players?.get(counter)?.name
        }

        // переход на таймер
        buttonStart?.setOnClickListener {
            val bundle = Bundle()

            bundle.apply {
                if (timer != null) {
                    putInt("timer", timer)
                }
                if (countSpy != null) {
                    putInt("countSpy", countSpy)
                }
                putParcelableArrayList("players", players)
            }
            findNavController().navigate(R.id.action_cardFragment_to_timerFragment, bundle)
        }

        //показать правила
        binding?.ivHelp?.setOnClickListener {
            findNavController().navigate(R.id.action_cardFragment_to_rulesFragment)
            if (!isBackOpened) {
                isBackOpened = !isBackOpened
            }
        }

        //"точно хотите выйти в меню?"
        binding?.ivMenu?.setOnClickListener {
            findNavController().navigate(R.id.action_cardFragment_to_exitToMenuFragment)
            if (!isBackOpened) {
                isBackOpened = !isBackOpened
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @SuppressLint("ResourceType")
    fun showBtnNext(players: ArrayList<Player>?) {
        if (counter == (players?.size?.minus(1) ?: Int)) {
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
            //flipOutAnimatorSet.interpolator = AccelerateDecelerateInterpolator()
            val flipInAnimatorSet =
                AnimatorInflater.loadAnimator(
                    context,
                    R.animator.flip_in
                ) as AnimatorSet
            //flipInAnimatorSet.interpolator = AccelerateDecelerateInterpolator()
            flipOutAnimatorSet.start()

            flipInAnimatorSet.setTarget(openedCard)
            flipInAnimatorSet.start()
            openedCard?.visibility = View.VISIBLE

            flipInAnimatorSet.doOnEnd {
            }
            isBackOpened = !isBackOpened
        } catch (ignored: Exception) {}
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