package com.googlenews.googlenews.model

data  class Article (
        var author: String? =null,
        var title: String,
        var description: String,
        var url: String,
        var urlToImage: String,
        var publishedAt: String)


