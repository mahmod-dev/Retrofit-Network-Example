package com.mahmouddev.retrofit_network_example.ui.points

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mahmouddev.retrofit_network_example.data.points.PointResponse
import com.mahmouddev.retrofit_network_example.data.points.PointService
import com.mahmouddev.retrofit_network_example.ui.BaseViewModel
import com.mahmouddev.retrofit_network_example.ui.ViewEvent
import com.mahmouddev.retrofit_network_example.util.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(
    private val pointService: PointService,
) : BaseViewModel() {
    override val TAG = "PointViewModel"

    val homeEvent: LiveData<Event<ViewEvent<PointResponse?>>> get() = _homeEvent

    private val _homeEvent = MutableLiveData<Event<ViewEvent<PointResponse?>>>()


    fun getPoints(latitude: Double?, longitude: Double?) {
        _homeEvent.postValue(Event(ViewEvent.Loading()))
        viewModelScope.launch(Dispatchers.IO) {
            fetchData(pointService.getPoints(latitude, longitude)) { event ->
                _homeEvent.postValue(Event(event))
            }
        }
    }


}

