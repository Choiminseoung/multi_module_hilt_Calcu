package com.example.mutil_module_hilt.di

import com.example.data.repository.CalculatorRepositoryImpl
import com.example.domain.repository.CalculatorRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module // Hilt 모듈임을 나타냄
@InstallIn(SingletonComponent::class) // 싱글톤으로 앱 전체에서 사용
abstract class RepositoryModule {

    @Binds // 인터페이스와 구현체를 바인딩
    @Singleton // 싱글톤으로 한번만 생성되고 재사용
    abstract fun bindCalculatorRepository(
        calculatorRepositoryImpl: CalculatorRepositoryImpl // 실제 구현체
    ) : CalculatorRepository

}