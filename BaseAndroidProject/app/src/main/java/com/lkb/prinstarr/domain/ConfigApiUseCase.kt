package com.lkb.prinstarr.domain

import com.lkb.prinstarr.usecase.base.UseCase

class ConfigApiUseCase constructor(private val configApiRepository: ConfigApiRepository):UseCase<LocalConfig,Any?>(){
    override suspend fun run(params: Any?): LocalConfig {
       return configApiRepository.getConfig(params.toString())
    }

}
