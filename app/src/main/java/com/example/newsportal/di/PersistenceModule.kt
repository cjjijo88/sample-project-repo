package com.example.newsportal.di

import com.example.newsportal.db.LocalDatabase
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * @Author: Jijo
 * @Date: 06-02-2022
 */
@Module
class PersistenceModule {
    @Provides
    fun providesLocalRealmDB(realm: Realm?): LocalDatabase {
        return LocalDatabase(realm!!)
    }

    @Provides
    fun providesRealm(realmConfiguration: RealmConfiguration?): Realm {
        return Realm.getInstance(realmConfiguration)
    }

    @Provides
    @Singleton
    fun providesRealmConfiguration(): RealmConfiguration {
        val builder = RealmConfiguration.Builder()
        builder.deleteRealmIfMigrationNeeded()
        // builder.inMemory();
        return builder.build()
    }
}