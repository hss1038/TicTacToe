package com.ssam.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var where = 0
    var player1WinsCounts = 0
    var player2WinsCounts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(view: View) {
        val btnSelected = view as Button
        var cellId = 0
        when(btnSelected.id){
            R.id.btn1 -> cellId = 1
            R.id.btn2 -> cellId = 2
            R.id.btn3 -> cellId = 3
            R.id.btn4 -> cellId = 4
            R.id.btn5 -> cellId = 5
            R.id.btn6 -> cellId = 6
            R.id.btn7 -> cellId = 7
            R.id.btn8 -> cellId = 8
            R.id.btn9 -> cellId = 9

        }
//        Log.d("buClick: ", btnSelected.id.toString())
//        Log.d("buClick: cellId ", cellId.toString())
        playGame(cellId, btnSelected)
    }


    fun playGame(cellId:Int, btnSelected:Button){
        if(activePlayer == 1){
            btnSelected.text = "X"
            btnSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            Log.d("player 1: ", player1.toString())
            activePlayer = 2
            btnSelected.isEnabled = false
            autoPlay()
        } else {
            btnSelected.text = "O"
            btnSelected.setBackgroundResource(R.color.darkGreen)
            player2.add(cellId)
            Log.d("player 2: ", player2.toString())
            activePlayer = 1
            btnSelected.isEnabled = false
        }


        where ++
        Log.d("where : ", where.toString()+"번,  여긴 못 올 걸 마지막에는...")
        checkWinner()
    }

    private fun autoPlay() {
        var emptyCells = ArrayList<Int>()

        for(cellId in 1..9){
            if(!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }


        if (emptyCells.size == 1){
            emptyCells.clear()
            restartGame()
        }


        Log.d("player empty: ", emptyCells.toString())
        Log.d("player empty size: ", emptyCells.size.toString())

        val r = Random()
        val randomIndex: Int = r.nextInt(emptyCells.size)
        val cellId = emptyCells[randomIndex]

        var btnSelected:Button?
        btnSelected = when(cellId){
            1 -> btn1
            2 -> btn2
            3 -> btn3
            4 -> btn4
            5 -> btn5
            6 -> btn6
            7 -> btn7
            8 -> btn8
            9 -> btn9
            else -> {btn1}
        }

        playGame(cellId, btnSelected)

        Toast.makeText(this, (emptyCells.size-1).toString()+"개 남음", Toast.LENGTH_LONG).show()
    }

    private fun checkWinner() {
        var winner = -1

        //row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(2) && player2.contains(3)){
            winner = 2
        }

        //row 2
        if(player1.contains(4) && player1.contains(5) && player1.contains(6)){
            winner = 1
        }
        if(player2.contains(4) && player2.contains(5) && player2.contains(6)){
            winner = 2
        }

        //row 3
        if(player1.contains(7) && player1.contains(8) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(7) && player2.contains(8) && player2.contains(9)){
            winner = 2
        }

        //col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7)){
            winner = 1
        }
        if(player2.contains(1) && player2.contains(4) && player2.contains(7)){
            winner = 2
        }

        //col 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8)){
            winner = 1
        }
        if(player2.contains(2) && player2.contains(5) && player2.contains(8)){
            winner = 2
        }

        //col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9)){
            winner = 1
        }
        if(player2.contains(3) && player2.contains(6) && player2.contains(9)){
            winner = 2
        }

        if(winner == 1){
            player1WinsCounts += 1
            Toast.makeText(this, "Player 1 win the game", Toast.LENGTH_LONG).show()
            restartGame()
        } else if(winner ==2) {
            player2WinsCounts += 1
            Toast.makeText(this, "Player 2 win the game", Toast.LENGTH_LONG).show()
            restartGame()
        }
    }



    fun restartGame() {
        where = 0
        activePlayer = 1
        player1.clear()
        player2.clear()

        for (cellId in 1..9) {
            var btnSelected: Button? = when (cellId) {
                1 -> btn1
                2 -> btn2
                3 -> btn3
                4 -> btn4
                5 -> btn5
                6 -> btn6
                7 -> btn7
                8 -> btn8
                9 -> btn9
                else -> {
                     btn1
                }
            }
            btnSelected!!.text = "true"
            btnSelected!!.setBackgroundResource(R.color.colorAccent)
            btnSelected!!.isEnabled = true
            Log.d("확인 :  ", btnSelected.toString())
        }


        Toast.makeText(this, "Player1 : $player1WinsCounts, Player2 : $player2WinsCounts", Toast.LENGTH_LONG).show()

    }
}

