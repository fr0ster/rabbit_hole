#!/bin/env bash

docker run -p 4369:4369 -p 5671:5671 -p 5672:5672 -d --hostname my-rabbit --name some-rabbit rabbitmq
