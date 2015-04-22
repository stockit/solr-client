package com.stockit.client

import java.io.IOException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.{SimpleTimeZone, Date}
import com.github.seratch.scalikesolr.Solr
import com.github.seratch.scalikesolr.request.query.{MaximumRowsReturned, Sort, Query}
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
            response.response.documents
        } catch {
            case e: IOException => {
                println("Error on query:" + request.queryString)
                throw e
            }
        }
    }

    def sortedByDate() = {
        val request = sortedByDateQuery
        try {
            val response = client.doQuery(request)
            response.response.documents
        } catch {
            case e: IOException => {
                println("Error on query:" + request.queryString)
                throw e
            }
        }

    }

    def sortedByDateQuery = {
        val request = new QueryRequest(Query("*:*"))
        request.setSort(Sort.as("historyDate asc"))
        request.setMaximumRowsReturned(new MaximumRowsReturned(30000))
        request
    }

    def dateQueryRequest(date: Date) = {
        val string = parseDate(date)
        new QueryRequest(Query("historyDate:" + string)) // dateToString(date)))
    }

    def parseDate(date: Date) = {
        parseDateString(dateToString(date))
    }

    def parseDateString(rawString: String) = {
        val withChars = rawString.substring(0,10) + "T" + rawString.substring(11, rawString.length) + "Z"
        withChars.replace(":", "\\:")
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
