package com.dbeginc.simpleweather.presentation.di

import javax.inject.Scope

/**
 * Created by darel on 26.03.18.
 *
 * This annotation class, create a scope in dagger dependencies graph
 * that it's will return same instance for all dependencies in this scope
 */
@Retention(AnnotationRetention.RUNTIME)
@Scope
annotation class ApplicationScope