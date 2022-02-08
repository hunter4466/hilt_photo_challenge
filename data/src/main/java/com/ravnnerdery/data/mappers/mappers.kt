package com.ravnnerdery.data.mappers

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}