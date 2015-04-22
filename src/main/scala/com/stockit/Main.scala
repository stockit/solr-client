package com.stockit

import java.util.Date

import com.stockit.client.Client

/**
 * Created by jmcconnell1 on 4/22/15.
 */
object Main extends App {
    val client = Client
    println(helpMessage)

    var ok = true
    while(ok) {
        val ln = scala.io.StdIn.readLine().replace("T", " ").replace("Z","")
        ok = ln != null
        val date = client.formatter.parse(ln)

        println("Result: " + client.fetch(date))
    }

    def helpMessage = {
        "Enter a date for example: " + client.parseDate(new Date()).replace("\\","")
    }
}