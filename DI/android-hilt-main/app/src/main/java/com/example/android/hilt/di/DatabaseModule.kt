package com.example.android.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.android.hilt.data.AppDatabase
import com.example.android.hilt.data.LogDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton



/**
 * @Module은 Hilt에 모듈임을 알려주는 것
 * @InstallIn은 어느 컨테이너에서 Hilt 구성요소를 사용해
 * 결합을 사용할 수 있는지 알려주는 것*/
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    /**
     * @Provides를 사용하면 Hilt에 생성자가 삽입될 수 없는 type을
     * 어떻게 제공하는지 알려줄 수 있음
     * LogDao 인스턴스를 제공할 때 AppDatabase에 대해서도 Hilt에
     * AppDatabase 인스턴스 제공하는 방법도 알려줘야 함
     */
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context) : AppDatabase{
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "logging.db"
        ).build()
    }

    @Provides
    fun provideLogDao(database : AppDatabase) : LogDao {
        return database.logDao()
    }
}