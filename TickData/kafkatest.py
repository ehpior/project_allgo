from kafka import KafkaProducer
from json import dumps
from random import randrange

producer = KafkaProducer(bootstrap_servers='127.0.0.1:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))
#, api_version=(0,11,5)



code = '005930'
time = '210312'
price = float(randrange(50000))
change_price = float(randrange(50000))
increase_rate = float(randrange(50000))
sell_1 = float(randrange(50000))
buy_1 = float(randrange(50000))
volume = float(randrange(50000))
cul_volume = float(randrange(50000))
cul_amount = float(randrange(50000))
open = float(randrange(50000))
high = float(randrange(50000))
low = float(randrange(50000))
plus_minus = float(randrange(50000))
a1 = float(randrange(50000))
a2 = float(randrange(50000))
a3 = float(randrange(50000))
turn_over = float(randrange(50000))
a4 = float(randrange(50000))
volume_power = float(randrange(50000))
capitalization = float(randrange(50000))
market = float(randrange(50000))
a5 = float(randrange(50000))
high_time = float(randrange(50000))
low_time = float(randrange(50000))

cheg_hk = [code, time, price, change_price, increase_rate, sell_1, buy_1,
                                    volume, cul_volume, cul_amount, open, high, low, plus_minus,
                                    a1, a2, a3, turn_over, a4, volume_power, capitalization,
                                    market, a5, high_time, low_time]


producer.send('hktest', value=cheg_hk)
producer.flush()