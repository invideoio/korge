package com.soywiz.korge.view

import com.soywiz.korge.render.*
import com.soywiz.korio.util.*
import com.soywiz.korma.geom.*

inline fun Container.fixedSizeContainer(width: Number, height: Number, clip: Boolean = false, callback: @ViewsDslMarker FixedSizeContainer.() -> Unit = {}) =
    FixedSizeContainer(width.toDouble(), height.toDouble(), clip).addTo(this).apply(callback)

open class FixedSizeContainer(
    override var width: Double = 100.0,
    override var height: Double = 100.0,
    var clip: Boolean = false
) : Container() {
    override fun getLocalBoundsInternal(out: Rectangle): Unit = Unit.run { out.setTo(0, 0, width, height) }

    override fun toString(): String {
        var out = super.toString()
        out += ":size=(${width.niceStr}x${height.niceStr})"
        return out
    }

    private val tempBounds = Rectangle()

    override fun renderInternal(ctx: RenderContext) {
        if (clip) {
            val c2d = ctx.ctx2d
            val bounds = getGlobalBounds(tempBounds)
            c2d.scissor(bounds) {
                super.renderInternal(ctx)
            }
        } else {
            super.renderInternal(ctx)
        }
    }
}
