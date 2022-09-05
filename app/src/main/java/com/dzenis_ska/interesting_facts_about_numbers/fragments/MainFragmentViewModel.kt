package com.dzenis_ska.interesting_facts_about_numbers.fragments

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import com.dzenis_ska.interesting_facts_about_numbers.App
import com.dzenis_ska.interesting_facts_about_numbers.model.local.LocalModel
import com.dzenis_ska.interesting_facts_about_numbers.model.remote.RemoteModel
import com.dzenis_ska.interesting_facts_about_numbers.utils.Event
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

data class StateUI(
    var isLoading: Int
) {
    companion object {
        val LOADING = StateUI(isLoading = 1)
        val LOADING_FROM_DB = StateUI(isLoading = 2)
        val NO_LOAD = StateUI(isLoading = 3)
    }
}

class MainFragmentViewModel(
    private val remoteModel: RemoteModel,
    private val localModel: LocalModel
) : ViewModel() {

    private val _state = MutableLiveData<StateUI>()
    val state: LiveData<StateUI> = _state

    init {
        getAllFactsFromHistory()
    }

    private val _factOfNumber = MutableLiveData<Event<Fact>>()
    val factOfNumber: LiveData<Event<Fact>> = _factOfNumber




    private val _factsFromHistory = MutableLiveData<List<Fact>>()
    val factsFromHistory: LiveData<List<Fact>> = _factsFromHistory


    fun getFactOfNumber(number: String) {
        viewModelScope.launch {
            Log.d("!!!getFactOfNumber", "")
            _state.value = StateUI.LOADING
            delay(3000)
            remoteModel.getFactOfNumber(number){fact->
                fact?.let{localModel.addToHistory(fact)}

                if (fact != null) _factOfNumber.value = Event(fact)
                else _factOfNumber.value = Event(Fact.DEFAULT)

                _state.value = StateUI.NO_LOAD
            }
        }
    }


    fun getFactOfRandomNumber() {
        viewModelScope.launch {
            Log.d("!!!getFactOfNumber", "")
            _state.value = StateUI.LOADING
            delay(3000)

            remoteModel.getFactOfRandomNumber() { fact ->
                fact?.let { localModel.addToHistory(fact) }
                if (fact != null) _factOfNumber.value = Event(fact)
                else _factOfNumber.value = Event(Fact.DEFAULT)
                _state.value = StateUI.NO_LOAD
            }
        }
    }


    fun getAllFactsFromHistory() {
        _state.value = StateUI.LOADING_FROM_DB

        Log.d("!!!getAllFactsFromHistory", "${state.value} -- ${_state.value}")

        viewModelScope.launch {
//
            delay(3000)
            localModel.getAllFactsFromHistory().map { list->
                list.map {
                    it.toFact()
                }
            }.collect{ listFFH->
                _factsFromHistory.value = listFFH
                _state.value = StateUI.NO_LOAD
                Log.d("!!!getAllFactsFromHistory", "${state.value} -- ${_state.value}")

            }

        }
    }


}

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        Log.d("!!!callbackFactory", "${modelClass}")
        val viewModel = when (modelClass) {
            MainFragmentViewModel::class.java -> {
                MainFragmentViewModel(app.remoteModel, app.localModel)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)


