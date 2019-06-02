package com.nelsjm.gol

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView


class GameView(context: Context, attributes: AttributeSet) : SurfaceView(context, attributes), SurfaceHolder.Callback {
    private val thread: GameThread
    private var cells: Array<Array<Cell>>? = null



    init {
        holder.addCallback(this)
        thread = GameThread(holder, this)

    }

    override fun surfaceCreated(p0: SurfaceHolder?) {
        if (cells == null) {
            val rowWidth = this.width - 100
            val rowCellCount = rowWidth/50

            val rowCount = (this.height - 100) / 50

            cells = Array(rowCount) {r -> Array(rowCellCount) { i ->  Cell(r, i,i * 50f + 50f, r * 50f + 50f, 50f, 50f, ((i+r) % 2 )== 0) } }

        }


        thread.setRunning(true)
        thread.start()
    }

    override fun surfaceDestroyed(p0: SurfaceHolder?) {
        var retry = true
        while (retry) {
            try {
                thread.setRunning(false)
                thread.join()
            } catch (e: Exception) {
                e.printStackTrace()
            }

            retry = false
        }
    }



    override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

    }

    /**
     * Function to update the positions of player and game objects
     */
    fun update() {
        if(cells != null) {
            cells!!.forEach { r -> r.forEach { c -> c.update(cells!!) } }
        }
    }

    /**
     * Everything that has to be drawn on Canvas
     */
    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        cells!!.forEach { r -> r.forEach { c -> c.draw(canvas) } }



    }

}