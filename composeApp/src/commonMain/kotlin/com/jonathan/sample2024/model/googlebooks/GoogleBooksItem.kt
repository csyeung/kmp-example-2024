package com.jonathan.sample2024.model.googlebooks

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GoogleBooksItem(
    val kind: String,
    val id: String,
    @SerialName("etag") val eTag: String,
    val selfLink: String,
    val volumeInfo: VolumeInfo,
    val saleInfo: SaleInfo,
    val accessInfo: AccessInfo,
    @SerialName("searchInfo") val searchInfo: SearchInfo? = null
)

@Serializable
data class VolumeInfo(
    val title: String,
    @SerialName("authors") val authors: List<String>? = null,
    val publisher: String? = null,
    val publishedDate: String,
    val pageCount: Int? = 0,
    val printType: String,
    val description: String? = null,
    val imageLinks: ImageLinks? = null,
    val maturityRating: String,
    val allowAnonLogging: Boolean,
    val contentVersion: String,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonicalVolumeLink: String
)

@Serializable
data class SaleInfo(
    val country: String,
    val saleability: String,
    val isEbook: Boolean
)

@Serializable
data class AccessInfo(
    val country: String,
    val viewability: String,
    val embeddable: Boolean,
    val publicDomain: Boolean,
    val textToSpeechPermission: String,
    val epub: Availability,
    val pdf: Availability,
    val webReaderLink: String,
    val accessViewStatus: String,
    val quoteSharingAllowed: Boolean
)

@Serializable
data class SearchInfo(
    val textSnippet: String
)

@Serializable
data class Availability(
    val isAvailable: Boolean,
)

@Serializable
data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)
