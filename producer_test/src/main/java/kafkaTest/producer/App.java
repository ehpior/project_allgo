package kafkaTest.producer;

import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * Hello world!
 *
 */
public class App {
	private static final String TOPIC_NAME = "hktest";
	private static final String FIN_MESSAGE = "exit";

	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "1.240.167.231:9092");
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Input > ");
			String message = sc.nextLine();
			ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, message);
			try {
				producer.send(record, (metadata, exception) -> {
					if (exception != null) {
						// some exception
					}
				});
			} catch (Exception e) {
				// exception
			} finally {
				producer.flush();
			}
			if (FIN_MESSAGE.equals(message)) {
				producer.close();
				break;
			}
		}
	}
}
