package com.softwaremill.httpserver

import java.io.{InputStream, OutputStream}
import java.net.InetSocketAddress

import com.sun.net.httpserver.{HttpExchange, HttpHandler, HttpServer}

object SimpleHttpServer {

  def main(args: Array[String]) {
    val server = HttpServer.create(new InetSocketAddress(8000), 0)
    server.createContext("/", new RootHandler())
    server.setExecutor(null)
    server.start()
  }

}

class RootHandler extends HttpHandler {

  def handle(t: HttpExchange) {

    copyStream(t.getRequestBody, System.out)

    val response = "Ack!"
    t.sendResponseHeaders(200, response.length())
    val os = t.getResponseBody
    os.write(response.getBytes)
    os.close()
  }

  private def copyStream(in: InputStream, out: OutputStream) {
    Iterator
      .continually(in.read)
      .takeWhile(-1 !=)
      .foreach(out.write)
  }

}
