from kafka import KafkaProducer
from json import dumps

producer = KafkaProducer(bootstrap_servers='1.240.167.231:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))
#, api_version=(0,11,5)

#print(producer.bootstrap_connected())
#for _ in range(100):

#data = {'id': 9, 'str1': "hk11", 'str2': "hk22"}
data = [9, "hk11", "hk22"]
#producer.send('hktest', b'exit')

producer.send('hktest', value=data)
producer.flush()