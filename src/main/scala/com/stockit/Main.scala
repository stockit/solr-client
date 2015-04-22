package com.stockit

import java.util.Date

import com.stockit.client.Client

/**
 * Created by jmcconnell1 on 4/22/15.
 */
object Main extends App {
    val client = Client
    println(helpMessage)
    val ln = scala.io.StdIn.readLine()
    val date = client.formatter.parse(ln)

    println("Result: " + client.fetch(date))

    def helpMessage = {
        "Enter a date below for example: " + client.formatter.format(new Date())
    }
}