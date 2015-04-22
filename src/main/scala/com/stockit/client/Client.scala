package com.stockit.client

import java.io.IOException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.{SimpleTimeZone, Date}
import com.github.seratch.scalikesolr.Solr
import com.github.seratch.scalikesolr.request.query.Query
import com.github.seratch.scalikesolr.request.QueryRequest

/**
 * Created by jmcconnell1 on 4/22/15.
 */
object Client {
    val host = "http://solr.deepdishdev.com:8983/solr"
    val client = Solr.httpServer(new URL(host + "/articleStock")).newClient
    var format: SimpleDateFormat = null

    def fetch(date: Date) = {
        val request = dateQueryRequest(date)
        try {
            val response = client.doQuery(request)
            response.response.documents foreach {
                case doc => {
                    println(doc.get("articleId").toString())
                }
            }
            response.response.documents
        } catch {
            case e: IOException => {
                println("Error on query:" + request.queryString)
                throw e
            }
        }
    }

    def dateQueryRequest(date: Date) = {
        new QueryRequest(Query("historyDate:" + "2013-09-18T05:00:00Z")) // dateToString(date)))
    }

    def dateToString(date: Date) = {
        formatter.format(date)
    }

    def formatter = {
        if (format == null) {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            format.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"))
        }
        format
    }
}
