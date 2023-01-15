/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.hilt.data

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Data manager class that handles data manipulation between the database and the UI.
 */

/** 애플리케이션 컨테이너에서 항상 동일한 인스턴스를 제공하게 해줌
 * 하지만 생성자를 보면 LogDao(전이 종속 항목)가 있음
 * 이 인스턴스를 제공하기 위해서는 LogDao를 어떻게 생성하는지에 대해서도
 * Hilt에 알려줘야 한다는 것. 그런데 LogDao는 인터페이스여서 생성자가 없음
 * 이럴 때 모듈을 사용해서 Hilt에 인스턴스 제공방법을 알려줌(@Module과 @InstallIn)
 */

class LoggerLocalDataSource @Inject constructor(private val logDao: LogDao) : LoggerDataSource {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val mainThreadHandler by lazy {
        Handler(Looper.getMainLooper())
    }

    override fun addLog(msg: String) {
        executorService.execute {
            logDao.insertAll(
                Log(
                    msg,
                    System.currentTimeMillis()
                )
            )
        }
    }

    override fun getAllLogs(callback: (List<Log>) -> Unit) {
        executorService.execute {
            val logs = logDao.getAll()
            mainThreadHandler.post { callback(logs) }
        }
    }

    override fun removeLogs() {
        executorService.execute {
            logDao.nukeTable()
        }
    }
}
