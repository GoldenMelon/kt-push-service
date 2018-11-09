package ru.whalekit.ktpushservice.executor

import ru.whalekit.ktpushservice.push.Push

interface PushExecutor {
    fun execute(push: Push)
}