package com.example.android.hilt.di

import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.AppNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Navigation은 Database와는 다른 행동 유형이므로 별개의 모듈을 생성
 * Database는 Application 컨테이너에 있지만, Navigation에는
 * Activity의 정보가 필요하기 때문에 별개로 생성
 * */
@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule {

    @Binds
    abstract fun bindNavigator(impl : AppNavigatorImpl) : AppNavigator
}