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
        val ln = scala.io.StdIn.readLine()
        ok = ln != null

        if (ln == "all") {
            println("Returned Documents: " + client.sortedByDate.size)
        } else if (ln == "exit") {
            System.exit(0)
        } else {
            val query = parseQuery(ln)
            val date = client.formatter.parse(query)
            println("Result: " + client.fetch(date))
        }
    }

    def parseQuery(line: String) = {
        line.replace("T", " ").replace("Z", "")
    }

    def helpMessage = {
        val line1 = "Enter a date for example: " + client.parseDate(new Date()).replace("\\","")
        List(line1, "Or type all for all documents").mkString("\n")
    }
}