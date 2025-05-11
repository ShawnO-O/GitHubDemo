package com.shawn.githubdemo.model.dto.user

import kotlinx.serialization.SerialName

//TODO 這邊SerialName不起作用...詭異
data class UserResponse(
    val avatar_url: String? = "",
    @SerialName("bio") val bio: Any? = null,
    @SerialName("blog") val blog: String = "",
    @SerialName("company") val company: Any? = null,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("email") val email: String = "",
    @SerialName("events_url") val eventsUrl: String = "",
    @SerialName("followers") val followers: Int = 0,
    @SerialName("followers_url") val followersUrl: String = "",
    @SerialName("following") val following: Int = 0,
    @SerialName("following_url") val followingUrl: String = "",
    @SerialName("gists_url") val gistsUrl: String = "",
    @SerialName("gravatar_id") val gravatarId: String = "",
    @SerialName("hireable") val hireable: Any? = null,
    @SerialName("html_url") val htmlUrl: String = "",
    @SerialName("id") val id: Int = 0,
    @SerialName("location") val location: Any? = null,
    @SerialName("login") val login: String = "",
    @SerialName("name") val name: Any? = null,
    val node_id: String = "",
    val notification_email: Any? = null,
    @SerialName("organizations_url") val organizationsUrl: String = "",
    @SerialName("public_gists") val publicGists: Int = 0,
    @SerialName("public_repos") val publicRepos: Int = 0,
    @SerialName("received_events_url") val receivedEventsUrl: String = "",
    @SerialName("repos_url") val reposUrl: String = "",
    @SerialName("site_admin") val siteAdmin: Boolean = false,
    @SerialName("starred_url") val starredUrl: String = "",
    @SerialName("subscriptions_url") val subscriptionsUrl: String = "",
    @SerialName("twitter_username") val twitterUsername: Any? = null,
    @SerialName("type") val type: String = "",
    @SerialName("updated_at") val updatedAt: String = "",
    @SerialName("url") val url: String = "",
    @SerialName("user_view_type") val userViewType: String = ""
)