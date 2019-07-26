package com.accenture.cfe.dev.rpncalc

import com.accenture.cfe.dev.rpncalc.service.CalculatorService
import com.accenture.cfe.dev.rpncalc.service.CalculatorServiceImpl
import com.accenture.cfe.dev.rpncalc.service.TranslatorService
import com.accenture.cfe.dev.rpncalc.service.TranslatorServiceImpl
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.koin.dsl.module
import org.koin.ktor.ext.Koin
import org.koin.ktor.ext.inject
import org.slf4j.event.Level

fun Application.main() {
  install(CORS) {
    anyHost()
  }
  install(CallLogging) {
    level = Level.INFO
  }
  install(Koin) {
    modules(calcModules)
  }

  val service: TranslatorService by inject()

  routing {
    get("/") {
      val queryParams = call.request.queryParameters
      val calcInput = queryParams["calc"] ?: ""
      try {
        val result = service.calculateInput(calcInput).toString()
        call.respondText { result }
      } catch (e: IllegalArgumentException) {
        println(e.stackTrace.toString())
        call.respondText { "Err" }
      }
    }
  }
}

val calcModules = module {
  single<TranslatorService> { TranslatorServiceImpl(get()) }
  single<CalculatorService> { CalculatorServiceImpl() }
}
