package com.rpn.totel.utils

sealed class LoadState {

    class OnSuccess(val data: Any?=null): LoadState(){
        override fun toString(): String {
            return "OnSuccess State"
        }
    }

    class OnFailure(val e: Exception? = null): LoadState(){
        override fun toString(): String {
            return "OnFailure State"
        }
    }

    object OnLoading : LoadState() {
        override fun toString(): String {
            return "OnLoading State"
        }
    }
}