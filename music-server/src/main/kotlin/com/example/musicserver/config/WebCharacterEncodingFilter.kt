package com.example.musicserver.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.nio.charset.StandardCharsets

@EnableWebMvc
@Configuration
class WebCharacterEncodingFilter : WebMvcConfigurer {
    /**
     * 乱码处理
     */
    fun responseBodyConverter(): HttpMessageConverter<String> {
        val converter = StringHttpMessageConverter(StandardCharsets.UTF_8)
        converter.setWriteAcceptCharset(false)
        return converter
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
    }

    override fun extendMessageConverters(converters: MutableList<HttpMessageConverter<*>?>) {
        if (converters.size > 0) {
            converters.add(converters[0])
            converters[0] = responseBodyConverter()
        } else {
            converters.add(responseBodyConverter())
        }
    }
}

