
package com.example.tf.fft.kotlin.model

import io.reactivex.Observable

interface IDataProvider<E> {
    fun getData() : Observable<E>
}