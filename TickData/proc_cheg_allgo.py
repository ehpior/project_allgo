#!/usr/bin/env python

""" client.py - Echo client for sending/receiving C-like structs via socket
References:
- Ctypes fundamental data types: https://docs.python.org/2/library/ctypes.html#ctypes-fundamental-data-types-2
- Ctypes structures: https://docs.python.org/2/library/ctypes.html#structures-and-unions
- Sockets: https://docs.python.org/2/howto/sockets.html
"""

#얘가 server이고 c가 client일때

import stockData
from pykiwoom.kiwoom import *
from PyQt5.QtWidgets import *
from PyQt5.QAxContainer import *
import redis
from kafka import KafkaProducer
from json import dumps
from datetime import datetime

""" This class defines a C-like struct """

def toFloat(item):
    result = 0.0
    try:
        result = float(item)
    except:
        pass
    return result

def toInt(item):
    result = 0
    try:
        result = int(item)
    except:
        pass
    return result

class MyWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        #self.db_redis = redis.StrictRedis(host='1.240.167.231', port=6379, db=0, password='wjdgusrl34', charset="utf-8",
        #                                  decode_responses=True)
        
        self.kafka_producer = KafkaProducer(bootstrap_servers='1.240.167.231:9092', value_serializer=lambda x: dumps(x).encode('utf-8'))

        self.today = datetime.now().strftime('%Y%m%d')

        self.setWindowTitle("Real")
        self.setGeometry(300, 300, 300, 600)

        btn1 = QPushButton("종목리스트 Get", self)
        btn1.resize(200, 60)
        btn1.move(20, 20)
        btn1.clicked.connect(self.btn_getStockList)

        btn2 = QPushButton("체결, 프로그램매매 Start", self)
        btn2.resize(200, 60)
        btn2.move(20, 100)
        btn2.clicked.connect(self.btn_registerRealData)

        btn5 = QPushButton("장시간체크 Start", self)
        btn5.resize(200, 60)
        btn5.move(20, 180)
        btn5.clicked.connect(self.btn_registerBusinessCheck)

        btn3 = QPushButton("체결Data TestSend", self)
        btn3.resize(200, 60)
        btn3.move(20, 350)
        btn3.clicked.connect(self.btn_testSendChegData)

        btn4 = QPushButton("프로그램Data TestSend", self)
        btn4.resize(200, 60)
        btn4.move(20, 430)
        btn4.clicked.connect(self.btn_testSendProgramData)

        btn4 = QPushButton("프로그램Data TestSend", self)
        btn4.resize(200, 60)
        btn4.move(20, 510)
        btn4.clicked.connect(self.btn_testSendBusinessData)

        self.ocx = QAxWidget("KHOPENAPI.KHOpenAPICtrl.1")
        self.ocx.OnEventConnect.connect(self._handler_login)
        self.ocx.OnReceiveRealData.connect(self._handler_real_data)
        self.CommmConnect()

    def btn_getStockList(self):
        self.ret = self.ocx.dynamicCall("GetCodeListByMarket(QString)", ["0"]).split(';')
        self.ret2 = self.ocx.dynamicCall("GetCodeListByMarket(QString)", ["10"]).split(';')

        self.stock_list = []
        self.stock_list.extend(self.ret)
        self.stock_list.extend(self.ret2)

        self.stock_list = list(filter(None, self.stock_list))
        self.stock_list.sort()

        print(str(len(self.ret)) + ', ' + str(len(self.ret2)) + ', ' + str(len(self.stock_list)))

    def btn_registerRealData(self):

        print("clicked")

        for i in range(len(self.stock_list)):
            screenNo = "1" + format(i, "03")

            setList = []

            if i == (len(self.stock_list) - 1):
                setList = self.stock_list[100*i:]
            else:
                setList = self.stock_list[100*i:100*(i+1)]

            self.SetRealReg(screenNo, ';'.join(setList), "10;131", 0)

        #self.SetRealReg("1000", ';'.join(self.ret[0:100]), "10;131", 0)

        print("finished")

    def btn_registerBusinessCheck(self):
        self.SetRealReg("2000", "", "215;20;214", 0)

        print("BusinessCheck called")

    def btn_testSendChegData(self):
        tick_cheg = stockData.test_cheg_data(self.ret)

        print(tick_cheg)

        try:
            self.kafka_producer.send('stock.cheg', value=tick_cheg)
            self.kafka_producer.flush()
        except Exception as e:
            print(e)

    def btn_testSendProgramData(self):
        tick_program = stockData.test_program_data(self.ret)

        try:
            self.kafka_producer.send('stock.program', value=tick_program)
            self.kafka_producer.flush()
        except Exception as e:
            print(e)

    def btn_testSendBusinessData(self):
        tick_business = ['143050', '9']

        try:
            self.kafka_producer.send('stock.business', value=tick_business)
            self.kafka_producer.flush()
        except Exception as e:
            print(e)

    def CommmConnect(self):
        self.ocx.dynamicCall("CommConnect()")
        self.statusBar().showMessage("login 중 ...")

    def _handler_login(self, err_code):
        if err_code == 0:
            self.statusBar().showMessage("login 완료")

    def _handler_real_data(self, code, real_type, data):

        if real_type == "주식체결":
            try:
                stock_code = code
                time = self.GetCommRealData(code, 20)
                price = toInt(self.GetCommRealData(code, 10))
                change_price = toInt(self.GetCommRealData(code, 11))
                increase_rate = toFloat(self.GetCommRealData(code, 12))
                volume = toInt(self.GetCommRealData(code, 15))
                cul_volume = toInt(self.GetCommRealData(code, 13))
                cul_amount = toInt(self.GetCommRealData(code, 14))
                open = toInt(self.GetCommRealData(code, 16))
                high = toInt(self.GetCommRealData(code, 17))
                low = toInt(self.GetCommRealData(code, 18))
                turn_over = toFloat(self.GetCommRealData(code, 31))
                volume_power = toFloat(self.GetCommRealData(code, 228))
                capitalization = toInt(self.GetCommRealData(code, 311))

                tick_cheg = [self.today + '-' + time, stock_code, abs(price), change_price, increase_rate,
                            volume, cul_volume, cul_amount, abs(open), abs(high), abs(low), turn_over,
                            volume_power, capitalization]

                self.kafka_producer.send('stock.cheg', value=tick_cheg)
                #self.kafka_producer.send('stock.price', value=[stock_code, abs(price)])
                self.kafka_producer.flush()
            except Exception as e:
                print(e)

        if real_type == "종목프로그램매매":  ##   ' won' 금액    →   단위당 백만원
            #index = int(self.stock_list.index(code))
            try:
                stock_code = code
                time = self.GetCommRealData(code, 20)
                sell_volume = toInt(self.GetCommRealData(code, 202))
                sell_amount = toInt(self.GetCommRealData(code, 204))
                buy_volume = toInt(self.GetCommRealData(code, 206))
                buy_amount = toInt(self.GetCommRealData(code, 208))
                net_buy_volume = toInt(self.GetCommRealData(code, 210))
                net_buy_amount = toInt(self.GetCommRealData(code, 212))

                tick_program = [self.today + '-' + time, stock_code, sell_volume, sell_amount, buy_volume,
                                    buy_amount, net_buy_volume, net_buy_amount]

                self.kafka_producer.send('stock.program', value=tick_program)
                self.kafka_producer.flush()
            except Exception as e:
                print(e)

        if real_type == "장시작시간":
            time = self.GetCommRealData(code, 20)
            state = self.GetCommRealData(code, 215)
            remained_time = self.GetCommRealData(code, 214)
            print(f'businessDay_state : {state}, {time}, {remained_time}')
            #self.db_redis.set('businessDay_state', state)

            tick_business = [self.today + '-' + time, state]

            self.kafka_producer.send('stock.business', value=tick_business)
            self.kafka_producer.flush()

    def SetRealReg(self, screen_no, code_list, fid_list, real_type):
        self.ocx.dynamicCall("SetRealReg(QString, QString, QString, QString)",
                              screen_no, code_list, fid_list, real_type)

    def DisConnectRealData(self, screen_no):
        self.ocx.dynamicCall("DisConnectRealData(QString)", screen_no)

    def GetCommRealData(self, code, fid):
        data = self.ocx.dynamicCall("GetCommRealData(QString, int)", code, fid)
        return data


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MyWindow()
    window.show()
    app.exec_()