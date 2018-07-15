extern crate amqp;
extern crate env_logger;

use amqp::{Session, Table, Basic, protocol};
use std::default::Default;

static AMPQ_HOST: &str = "amqp://localhost//";
static SEND_TXT: &str = "Hello, RabbitMQ!!!";

fn main() {
    env_logger::init().unwrap();
    let amqp_url: String = AMPQ_HOST.to_string();
    let mut session = match Session::open_url(&amqp_url[..]) {
        Ok(session) => session,
        Err(error) => panic!("Can't create session: {:?}", error)
    };
    let mut channel = session.open_channel(1).ok().expect("Can't open channel!");

    let queue_name = "test_queue";
    channel.queue_declare(queue_name, false, true, false, false, false, Table::new()).ok().expect("Unable to declare queue!");

    println!("We will be sent {} into the '{}' queue.", SEND_TXT, queue_name);
    let input_data: String = SEND_TXT.to_string();

    println!("Sending data...\n");
    channel.basic_publish("", queue_name, true, false,
        protocol::basic::BasicProperties{ content_type: Some("text".to_string()), ..Default::default()},
        input_data.trim_right().to_string().into_bytes()).unwrap();

     for get_result in channel.basic_get(queue_name, false) {
        println!("Received: {:?}", String::from_utf8_lossy(&get_result.body));
        get_result.ack();
    }

    println!("Queue is now empty, quitting...");
    channel.close(200, "Bye");
    session.close(200, "Good Bye");
}
