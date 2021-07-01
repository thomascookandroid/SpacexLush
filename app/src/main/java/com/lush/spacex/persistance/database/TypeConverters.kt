package com.lush.spacex.persistance.database

import androidx.room.TypeConverter
import com.lush.spacex.persistance.entities.launch.LaunchFailure
import com.lush.spacex.persistance.entities.launch.LaunchPadCore
import com.lush.spacex.persistance.entities.rocket.PayloadWeight
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.IllegalArgumentException
import java.text.DateFormat
import java.util.*

object TypeConverters {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
        .build()

    private val stringListType = Types.newParameterizedType(
        List::class.java,
        String::class.java
    )

    private val payloadListType = Types.newParameterizedType(
        List::class.java,
        PayloadWeight::class.java
    )

    private val launchFailureListType = Types.newParameterizedType(
        List::class.java,
        LaunchFailure::class.java
    )

    private val launchPadCoreListType = Types.newParameterizedType(
        List::class.java,
        LaunchPadCore::class.java
    )

    @TypeConverter
    @JvmStatic
    fun restoreLaunchFailureList(listOfString: String): List<LaunchFailure> {
        return moshi.adapter<List<LaunchFailure>>(launchFailureListType).fromJson(listOfString) ?: emptyList()
    }

    @TypeConverter
    @JvmStatic
    fun saveLaunchFailureList(listOfPayload: List<LaunchFailure>): String {
        return moshi.adapter<List<LaunchFailure>>(launchFailureListType).toJson(listOfPayload)
    }

    @TypeConverter
    @JvmStatic
    fun restoreLaunchPadCoreList(listOfString: String): List<LaunchPadCore> {
        return moshi.adapter<List<LaunchPadCore>>(launchPadCoreListType).fromJson(listOfString) ?: emptyList()
    }

    @TypeConverter
    @JvmStatic
    fun saveLaunchPadCoreList(listOfPayload: List<LaunchPadCore>): String {
        return moshi.adapter<List<LaunchPadCore>>(launchPadCoreListType).toJson(listOfPayload)
    }

    @TypeConverter
    @JvmStatic
    fun restorePayloadWeightList(listOfString: String): List<PayloadWeight> {
        return moshi.adapter<List<PayloadWeight>>(payloadListType).fromJson(listOfString) ?: emptyList()
    }

    @TypeConverter
    @JvmStatic
    fun savePayloadWeightList(listOfPayload: List<PayloadWeight>): String {
        return moshi.adapter<List<PayloadWeight>>(payloadListType).toJson(listOfPayload)
    }

    @TypeConverter
    @JvmStatic
    fun restoreStringList(listOfString: String): List<String> {
        return moshi.adapter<List<String>>(stringListType).fromJson(listOfString) ?: emptyList()
    }

    @TypeConverter
    @JvmStatic
    fun saveStringList(listOfString: List<String>): String {
        return moshi.adapter<List<String>>(stringListType).toJson(listOfString)
    }

    @TypeConverter
    @JvmStatic
    fun restoreDate(date: String): Date {
        return DateFormat.getDateInstance().parse(date)
            ?: throw IllegalArgumentException("$date is provided in wrong format")
    }

    @TypeConverter
    @JvmStatic
    fun saveDate(date: Date): String {
        return DateFormat.getDateInstance().format(date)
    }
}