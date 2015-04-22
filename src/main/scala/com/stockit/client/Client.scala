package com.stockit.client

import java.net.URL
import com.github.seratch.scalikesolr.Solr
import com.github.seratch.scalikesolr.request.query.Query
import com.github.seratch.scalikesolr.request.QueryRequest

/**
 * Created by jmcconnell1 on 4/22/15.
 */
object Client {
    val host = "http://solr.deepdishdev.com:8983/solr"
    val client = Solr.httpServer(new URL(host + "/articleStock")).newClient


    def fetch() = {
        val request = new QueryRequest(Query("articleId:15726"))
        val response = client.doQuery(request)
        response.response.documents foreach {
            case doc => {
                println(doc.get("articleId").toString())
            }
        }
    }
}
