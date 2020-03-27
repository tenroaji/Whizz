package id.magau.whizz.data.model

/**
 * Created by Anwar on 1/26/2020.
 */

sealed class State<out T : Any> {
    data class Success<out T : Any>(val data: T) : State<T>()
    data class Error<out T : Any?>(val message: String? = null, val error: T? = null) : State<Nothing>()
}