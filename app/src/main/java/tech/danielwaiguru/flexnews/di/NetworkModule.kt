package tech.danielwaiguru.flexnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {
    const val BASE_URL = "https://newsapi.org"
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    @Singleton
    @Provides
    fun provideMoshi(): Converter.Factory = MoshiConverterFactory.create()
    @Singleton
    @Provides
    fun provideRetrofitInstance(client: OkHttpClient, factory: Converter.Factory): Retrofit =
        Retrofit.Builder()
        .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .build()
}