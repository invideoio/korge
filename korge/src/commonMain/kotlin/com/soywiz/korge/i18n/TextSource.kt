package com.soywiz.korge.i18n

import com.soywiz.korio.i18n.*

interface TextSource {
	fun getText(language: Language): String
}
