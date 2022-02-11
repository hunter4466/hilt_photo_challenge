package com.ravnnerdery.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ravnnerdery.data.mappers.DomainMapper
import com.ravnnerdery.domain.models.PhotoInfo

@Entity(tableName = "photo_table")
class PhotoInfoEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Long,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "url")
    var url: String,
    @ColumnInfo(name = "thumbnailUrl")
    var thumbnailUrl: String
) : DomainMapper<PhotoInfo> {
    override fun mapToDomainModel(): PhotoInfo = PhotoInfo(
        id, title, url, thumbnailUrl
    )
}
