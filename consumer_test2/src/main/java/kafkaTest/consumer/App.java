package kafkaTest.consumer;

import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONArray;

/**
 * Hello world!
 *
 */
public class App {
	private static final String TOPIC_NAME = "hktest";
	private static final String FIN_MESSAGE = "exit";
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "hktest2");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
		consumer.subscribe(Collections.singletonList(TOPIC_NAME));
		String message = null;
		try {
			do {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				for (ConsumerRecord<String, String> record : records) {
					message = record.value();
					
					System.out.println(message);
					JSONArray jAry = new JSONArray(message);
					
					System.out.println(jAry.getString(0));
					System.out.println(jAry.getString(1));
					System.out.println(jAry.getFloat(2));
					
					testDto tDto = testDto.builder()
							.str1(jAry.getString(0))
							.str2(jAry.getString(1))
							.id(jAry.getInt(2))
							.build();
					
					System.out.println(tDto.toString());
					
/*					ObjectMapper objMapper = new ObjectMapper();
					testDto tDto = objMapper.readValue(message, testDto.class);
					System.out.println(tDto.toString());*/
				}
			} while (!FIN_MESSAGE.equals(message));
		} catch (Exception e) {
			System.out.println(e);
			// exception
		} finally {
			consumer.close();
		}
	}
}
