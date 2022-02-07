package com.example.newsportal.di

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier

/**
 * @Author: Jijo
 * @Date: 05-02-2022
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
annotation class AppContext()
