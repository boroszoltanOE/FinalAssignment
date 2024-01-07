package com.zoltanboros.finalassignment.model
import java.util.concurrent.atomic.AtomicInteger


data class User(
    var id: Int ,
    var userName: String,
    var email: String,
    var password: String,
    var isActive: Boolean = false
) {

    constructor() : this(1, "", "", "", false)

    object GlobalState {
        private var value = 0;

        fun getNextInt(): Int = value++
    }

}
