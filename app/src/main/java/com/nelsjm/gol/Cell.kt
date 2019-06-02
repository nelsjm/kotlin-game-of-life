package com.nelsjm.gol

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class Cell(public val row: Int, public val col: Int, private val x: Float, private val y: Float, private val width: Float, private val height: Float, public var alive: Boolean) {

    private var nextState: Boolean = true

    init {
        nextState = alive
    }

    fun update(cells: Array<Array<Cell>>) {
        alive = !alive
    }

   fun draw(canvas: Canvas) {
        val paint = Paint()
       paint.alpha = 255
       paint.color = Color.CYAN
       if (!alive) {
           paint.color = Color.RED
       }

       paint.strokeWidth = 3f

       canvas.drawRect(x, y, x + width, y+height, paint)
    }

}