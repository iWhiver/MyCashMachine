package com.belorechev.cashmachine

class UrlMappings {

    static mappings = {
        "/"(controller: 'lifeCircle')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
