package tech.goksi.tabbyfiles.annotations

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@Component
@MustBeDocumented
@RequestMapping("/api/")
annotation class ApiController(
    @get:AliasFor(annotation = Component::class)
    val value: String = ""
)
