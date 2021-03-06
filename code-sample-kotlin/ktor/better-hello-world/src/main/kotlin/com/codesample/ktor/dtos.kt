@file:UseSerializers(
    UUIDSerializer::class, OffsetDateTimeSerializer::class, LocalDateTimeSerializer::class, LocalDateSerializer::class
)

package com.codesample.ktor

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDateTime
import java.util.*


@Serializable
data class UsersDto(val users: List<UserDto>)

@Serializable
data class UserDto(
    val uuid: UUID,
    var promocode: String? = null,
    val createdAt: LocalDateTime
)
