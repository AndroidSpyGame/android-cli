package ru.kpfu.itis.spymasters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import ru.kpfu.itis.spymasters.databinding.FragmentCardBinding


class CardFragment : Fragment(R.layout.fragment_card) {

    private var buttonRole: Button? = null
    private var buttonNext: Button? = null
    private var buttonStart: Button? = null

    private var category: TextView? = null
    private var binding: FragmentCardBinding? = null
    private var stateUi: State  = State.CloseCard
    private val listPlaces: List<String> = PlaceRepository.list
    private val listPlayers: List<String> = PlayersRepository.list
    private val place: String = listPlaces[(0..listPlaces.size).random()]
    var counter = 0
    var spyNumber = (0..listPlayers.size).random()

    @SuppressLint("ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCardBinding.bind(view)

        buttonRole = binding?.btnRole
        buttonNext = binding?.btnNext
        buttonStart = binding?.btnStart
        category = binding?.tvOneOfCategory
        binding?.player?.text = listPlayers[counter]

        buttonRole?.setOnClickListener {
            when(stateUi){
                State.CloseCard -> {
                    stateUi =
                        if (spyNumber != counter) {
                        openCard(binding?.cvSmallSimplePlayer)
                        State.SimplePlayer}
                    else {
                        openCard(binding?.cvSmallSpymaster)
                        State.Spymaster}
                }
                State.SimplePlayer -> {
                    closeCard(binding?.cvSmallSimplePlayer)
                    stateUi = State.CloseCard
                }
                State.Spymaster -> {
                    closeCard(binding?.cvSmallSpymaster)
                    stateUi = State.CloseCard
                }
            }
        }


        buttonNext?.setOnClickListener {
            if (counter == listPlayers.size - 1) {
                buttonNext?.visibility = View.GONE
            } else {
                counter++
                buttonNext?.visibility = View.GONE
                when (stateUi) {
                    State.CloseCard -> {
//                    binding?.cvSmallSimplePlayer?.visibility = View.GONE
//                    binding?.cvSmallSpymaster?.visibility = View.GONE
//                    binding?.cvSmallClose?.visibility = View.VISIBLE
                        binding?.player?.text = listPlayers[counter]
                        stateUi = State.CloseCard
                    }

                    State.SimplePlayer -> {
                        binding?.player?.text = listPlayers[counter]
                        closeCard(binding?.cvSmallSimplePlayer)
                        stateUi = State.CloseCard
                    }

                    State.Spymaster -> {
                        binding?.player?.text = listPlayers[counter]
                        closeCard(binding?.cvSmallSpymaster)
                        stateUi = State.CloseCard
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    sealed class State( ) {
        object Spymaster: State()
        object SimplePlayer: State()
        object CloseCard: State()
    }

    fun openCard(crdVw: CardView?) {
        if (counter == listPlayers.size - 1) {
            buttonNext?.visibility = View.GONE
            buttonStart?.visibility = View.VISIBLE
        } else {
            buttonNext?.visibility = View.VISIBLE // кнопка становится видимой
        }
        crdVw?.visibility = View.VISIBLE
        category?.text = place
        binding?.cvSmallClose?.visibility = View.GONE
        buttonRole?.text = "Скрыть"
    }

    fun closeCard(cardView: CardView?) {
        cardView?.visibility = View.GONE
        binding?.cvSmallClose?.visibility = View.VISIBLE
//                    it.setBackgroundResource(R.drawable.button_bg_color)
        buttonRole?.text = "Роль"
    }
}