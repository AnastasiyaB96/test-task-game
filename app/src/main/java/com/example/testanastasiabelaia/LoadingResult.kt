package com.example.testanastasiabelaia

enum class LoadingResult(val value: Int) {
    SHOW_GAME(1), SHOW_WEB_VIEW(2), WAITING(-1), ERROR(-1);

    companion object {
        fun fromValue(_value: Int): LoadingResult? {
            return when (_value) {
                1 -> SHOW_GAME
                2 -> SHOW_WEB_VIEW
                else -> null
            }
        }
    }
}