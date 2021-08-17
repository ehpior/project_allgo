from random import randrange

def test_cheg_data(codes):
    rand_num = randrange(len(codes))
    code = codes[rand_num]
    print("test_cheg_code : " + codes[rand_num])
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

    return cheg_hk

def test_program_data(codes):
    rand_num = randrange(len(codes))
    code = codes[rand_num]
    print("test_program_code : " + codes[rand_num])
    time = '140130'
    sell_volume = randrange(50000)
    sell_amount = randrange(50000)
    buy_volume = randrange(50000)
    buy_amount = randrange(50000)
    net_buy_volume = randrange(50000)
    net_buy_amount = randrange(50000)

    cheg_hk = [code, time, sell_volume, sell_amount,
               buy_volume, buy_amount, net_buy_volume, net_buy_amount]

    return cheg_hk