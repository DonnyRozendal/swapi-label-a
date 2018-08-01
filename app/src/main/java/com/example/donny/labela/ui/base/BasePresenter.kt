package com.example.donny.labela.ui.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : MvpView> {

    private val disposables = CompositeDisposable()
    var view: T? = null

    fun bind(view: T) {
        this.view = view
    }

    fun unbind() {
        this.view = null
    }

    fun destroy() {
        disposables.dispose()
        unbind()
    }

}