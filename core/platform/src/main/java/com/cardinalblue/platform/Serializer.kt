package com.cardinalblue.platform

import android.graphics.Rect
import android.net.Uri
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import java.util.UUID

object Serializer {
    val moshi: Moshi by lazy(LazyThreadSafetyMode.NONE) {
        Moshi.Builder()
            .add(uuidAdapter)
            .add(uriAdapter)
            .add(rectAdapter)
            .build()
    }

    private val uuidAdapter = object : JsonAdapter<UUID>() {
        @FromJson
        override fun fromJson(reader: JsonReader): UUID? {
            return if (reader.hasNext()) {
                val value = reader.nextString()
                if (value != "null") {
                    UUID.fromString(value)
                } else {
                    null
                }
            } else {
                null
            }
        }

        @ToJson
        override fun toJson(writer: JsonWriter, value: UUID?) {
            writer.value(value.toString())
        }
    }

    private val uriAdapter = object : JsonAdapter<Uri>() {
        @FromJson
        override fun fromJson(reader: JsonReader): Uri? {
            return Uri.parse(reader.nextString())
        }

        @ToJson
        override fun toJson(writer: JsonWriter, value: Uri?) {
            writer.value(value.toString())
        }
    }

    private val rectAdapter = object : JsonAdapter<Rect>() {
        @FromJson
        override fun fromJson(reader: JsonReader): Rect {
            reader.beginObject()
            val result = Rect(
                reader.nextInt(),
                reader.nextInt(),
                reader.nextInt(),
                reader.nextInt()
            )
            reader.endObject()
            return result
        }

        @ToJson
        override fun toJson(writer: JsonWriter, value: Rect?) {
            writer.beginObject()
            writer.name("left").value(value?.left)
            writer.name("top").value(value?.top)
            writer.name("right").value(value?.right)
            writer.name("bottom").value(value?.bottom)
            writer.endObject()
        }
    }
}

inline fun <reified T> T.serialize(): String = Serializer.moshi.adapter(T::class.java).toJson(this)
inline fun <reified T> String.deserialize(): T =
    Serializer.moshi.adapter(T::class.java).fromJson(this) ?: error("Invalid JSON: $this")