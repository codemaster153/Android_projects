package com.lkb.prinstarr.usecase

import com.lkb.prinstarr.domain.ConfigApiRepository
import com.lkb.prinstarr.domain.LocalConfig
import com.lkb.prinstarr.usecase.base.UseCase

class ConfigUseCase constructor(private val configApiRepository: ConfigApiRepository) :
    UseCase<LocalConfig, Any?>() {
    override suspend fun run(params: Any?): LocalConfig {
        return configApiRepository.getConfig(params.toString())
    }
}