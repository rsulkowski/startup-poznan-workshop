package com.tomtom.example.areanotifier.utils.arch

class Resource<out T> constructor(val status: Status, val data: T?, val error: Error?) {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> loading(data: T?): Resource<T> =
            Resource(
                Status.LOADING,
                data,
                null
            )

        fun <T> success(data: T?): Resource<T> =
            Resource(
                Status.SUCCESS,
                data,
                null
            )

        fun <T> error(data: T?, error: Error?): Resource<T> =
            Resource(
                Status.ERROR,
                data,
                error
            )
    }

}