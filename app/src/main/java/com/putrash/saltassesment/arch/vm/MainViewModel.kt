package com.putrash.saltassesment.arch.vm

import android.app.Application
import android.content.Context
import android.telephony.TelephonyManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.putrash.common.Event
import com.putrash.common.capitalize
import com.putrash.data.Api
import com.putrash.data.BuildConfig
import com.putrash.data.model.Article
import com.putrash.saltassesment.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val api: Api) : BaseViewModel() {
    private val telephonyManager = application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

    private val _articles = MutableLiveData<Event<ArrayList<Article>>>()
    val articles: LiveData<Event<ArrayList<Article>>> get() = _articles

    private fun getCountryCode(): String {
        return telephonyManager.networkCountryIso
    }

    fun getHeadlines() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val countryCode = getCountryCode()
                val result = api.getTopHeadlines(
                    country = countryCode,
                    apiKey = BuildConfig.API_KEY
                )
                when (result.status) {
                    "ok" -> {
                        _articles.postValue(Event(result.articles))
                    }
                    else -> showError(result.message)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                showError(throwable.message.toString().capitalize())
            } finally {
                hideLoading()
            }
        }
    }

    fun getNews(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val countryCode = getCountryCode()
                val result = api.getTopHeadlines(
                    country = countryCode,
                    category = category,
                    apiKey = BuildConfig.API_KEY
                )
                when (result.status) {
                    "ok" -> _articles.postValue(Event(result.articles))
                    else -> showError(result.message)
                }
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
                showError(throwable.message.toString().capitalize())
            } finally {
                hideLoading()
            }
        }
    }

}