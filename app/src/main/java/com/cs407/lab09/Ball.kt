package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        // TODO: Call reset()
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        val a0x = accX
        val a1x = xAcc

        val a0y = accY
        val a1y = yAcc

        if (isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        val lX = velocityX * dT + (1f / 6f) * dT * dT * (3f * a0x + a1x)
        val lY = velocityY * dT + (1f / 6f) * dT * dT * (3f * a0y + a1y)

        posX += lX
        posY += lY

        val vX = velocityX + 0.5f * (a0x + a1x) * dT
        val vY = velocityY + 0.5f * (a0y + a1y) * dT

        velocityX = vX
        velocityY = vY

        accX = a1x
        accY = a1y
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    fun checkBoundaries() {
        // TODO: implement the checkBoundaries function
        // (Check all 4 walls: left, right, top, bottom)
        // Left Wall (Boundary: 0)
        if (posX < 0f) {
            posX = 0f
            velocityX = 0f
            accX = 0f
        }

        // Right Wall (Boundary: backgroundWidth - ballSize)
        val rightBoundary = backgroundWidth - ballSize
        if (posX > rightBoundary) {
            posX = rightBoundary
            velocityX = 0f
            accX = 0f
        }

        // Top Wall (Boundary: 0)
        if (posY < 0f) {
            posY = 0f
            velocityY = 0f
            accY = 0f
        }

        // Bottom Wall (Boundary: backgroundHeight - ballSize)
        val bottomBoundary = backgroundHeight - ballSize
        if (posY > bottomBoundary) {
            posY = bottomBoundary
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // TODO: implement the reset function
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        posX = backgroundWidth / 2f - ballSize / 2f
        posY = backgroundHeight / 2f - ballSize / 2f

        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
        isFirstUpdate = true
    }
}