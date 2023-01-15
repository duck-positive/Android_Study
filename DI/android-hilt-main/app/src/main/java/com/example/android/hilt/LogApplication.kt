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

package com.example.android.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
                애플리케이션 컨테이너 생성. 앱의 상위 컨테이너이므로 다른 컨테이너에서
                이 컨테이너가 제공하는 종속 항목에 접근 가능.

                컨테이너란?
                컨테이너는 앱 인스턴스 생성을 어떻게 하는지 알고 있음
                어떻게 하는지 알기 위해서는 그 인스턴스를 생성하는데
                필요한 종속항목의 관리가 필요함
                컨테이너는 자기가 제공하는 인스턴스를 가져올 수 있는 메서드를
                보여줌. 메서드는 항상 다른 인스턴스(싱글톤 패턴과 반대)를 제공하거나
                동일한 인스턴스를 제공할 수 있음. 동일한 인스턴스를 제공한다면
                해당 인스턴스는 컨테이너로 범위가 지정됨.
                [참고] https://developer.android.com/training/dependency-injection/hilt-android?hl=ko
                */
@HiltAndroidApp
class LogApplication : Application()
