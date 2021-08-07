package com.lkb.prinstarr.view.login

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lkb.prinstarr.Util
import com.lkb.prinstarr.domain.LocalConfig
import com.lkb.prinstarr.usecase.ConfigUseCase
import com.lkb.prinstarr.usecase.base.UseCaseResponse

class LoginViewModel(
    private val pref: SharedPreferences,
    private val configUseCase: ConfigUseCase
) :
    ViewModel() {
     var localConfig:LocalConfig ? = null
    private var _shouldRun = MutableLiveData<Boolean>()
    var shouldRun:LiveData<Boolean> = _shouldRun

    fun getConfiguration(){
        val url = pref.getString("config", "http://localhost/")
        configUseCase.invoke(viewModelScope, url, object : UseCaseResponse<LocalConfig> {
            override fun onSuccess(result: LocalConfig) {
                localConfig = result
                if (result.dbPath != null) {
                    Util.updatePref(pref, "dbPath", result.dbPath)
                }
            }

            override fun onError(apiError: String?) {
                Util.dLog(apiError.toString())
                _shouldRun.value = false
            }

        })
    }

    fun isStringPrefsPresent(key: String): Boolean? {
        return pref.getString(key, "")?.isNotEmpty()
    }

    fun getPrefValue(key: String): String? {
        return pref.getString(key, "")
    }

    fun createPreference(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun isValidConfig(): Boolean {
        return true
    }

    fun hasRunPermission(): Boolean {
        return true
    }

}