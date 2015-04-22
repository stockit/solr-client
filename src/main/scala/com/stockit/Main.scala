package com.stockit

import com.stockit.client.Client

/**
 * Created by jmcconnell1 on 4/22/15.
 */
object Main extends App {
    val client = Client
    println(client.fetch())
}
