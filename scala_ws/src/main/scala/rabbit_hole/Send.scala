package rabbit_hole

import com.rabbitmq.client.ConnectionFactory

object Send {

  private val QUEUE_NAME = "test_queue"

  def main(argv: Array[String]) {
    val factory = new ConnectionFactory()
    factory.setHost("localhost")
    val connection = factory.newConnection()
    val channel = connection.createChannel()
    channel.queueDeclare(QUEUE_NAME, true, false, false, null)
    val message = "Hello World!"
    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"))
    println(" [x] Sent '" + message + "'")
    channel.close()
    connection.close()
  }
}
