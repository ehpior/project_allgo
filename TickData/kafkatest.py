from kafka import KafkaProducer
from json import dumps
from random import randrange

producer = KafkaProducer(bootstrap_servers='1.240.167.231:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))
#, api_version=(0,11,5)

for i in range(1):
    code = '005930'
    time = '140130'
    price = randrange(50000)
    change_price = randrange(50000)
    increase_rate = float(randrange(50000))
    volume = randrange(50000)
    cul_volume = randrange(50000)
    cul_amount = randrange(50000)
    open = randrange(50000)
    high = randrange(50000)
    low = randrange(50000)
    turn_over = float(randrange(50000))
    volume_power = float(randrange(50000))
    capitalization = randrange(50000)

    cheg_hk = [code, time, price, change_price, increase_rate,
               volume, cul_volume, cul_amount, open, high, low, turn_over,
               volume_power, capitalization]


    producer.send('tick.cheg', value=cheg_hk)
    producer.flush()