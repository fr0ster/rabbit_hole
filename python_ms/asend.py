#!/usr/bin/env python
import asyncio
import pika


rabbitmq_host = 'localhost'
# rabbitmq_host = '10.3.21.82'


async def say_hello(count=1):
    connection = pika.BlockingConnection(
            pika.ConnectionParameters(rabbitmq_host))
    channel = connection.channel()
    channel.queue_declare(queue='hello')
    for i in range(count):
        channel.basic_publish(
                exchange='',
                routing_key='hello',
                body='Hello World!')
        print(" [x] Sent 'Hello World!'")
        await asyncio.sleep(0.001)
    connection.close()


async def say_goodby(count=1):
    connection = pika.BlockingConnection(
            pika.ConnectionParameters(rabbitmq_host))
    channel = connection.channel()
    channel.queue_declare(queue='hello')
    for i in range(count):
        channel.basic_publish(
                exchange='',
                routing_key='hello',
                body='Goodby World!')
        await asyncio.sleep(0.001)
        print(" [x] Sent 'Goodby World!'")
    connection.close()


loop = asyncio.get_event_loop()
loop.run_until_complete(
        asyncio.gather(
            say_hello(100),
            say_goodby(100)
            )
        )
loop.close()
