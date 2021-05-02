package com.lkb.prinstarr.usecase.base


interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: String?)
}

