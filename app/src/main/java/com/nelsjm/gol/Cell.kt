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
        var aliveNeighbors = 0
        var lc = this.col - 1
        if (lc < 0) {
            lc = cells[0].size - 1
        }

        var rc = this.col + 1
        if (rc == cells[0].size) {
            rc = 0
        }

        var ur = this.row - 1
        if (ur < 0 ) {
            ur = cells.size - 1
        }

        var lr = this.row + 1
        if (lr == cells.size) {
            lr = 0
        }

        // upper left
        if (cells[ur][lc].alive) {
            aliveNeighbors ++
        }

        // upper center
        if(cells[ur][this.col].alive) {
            aliveNeighbors++
        }

        // upper right
        if(cells[ur][rc].alive) {
            aliveNeighbors++
        }

        // left
        if(cells[this.row][lc].alive) {
            aliveNeighbors ++
        }

        // right
        if(cells[this.row][rc].alive) {
            aliveNeighbors++
        }

        // lower left
        if(cells[lr][lc].alive) {
            aliveNeighbors ++
        }

        // lower center
        if(cells[lr][this.col].alive) {
            aliveNeighbors ++
        }

        // lower right
        if (cells[lr][rc].alive) {
            aliveNeighbors++
        }

        if (this.alive) {
            if (aliveNeighbors < 2) {
                nextState = false
            }

            if (aliveNeighbors == 2 || aliveNeighbors ==3) {
                nextState=true
            }

            if (aliveNeighbors > 3) {
                nextState = false
            }
        } else {
            if (aliveNeighbors == 3) {
                nextState = true
            }
        }

    }

   fun draw(canvas: Canvas) {
       alive = nextState

       val paint = Paint()
       paint.alpha = 255
       paint.color = Color.CYAN
       if (!alive) {
           paint.color = Color.BLACK
       }

       paint.strokeWidth = 3f

       canvas.drawRect(x, y, x + width, y+height, paint)
    }

}