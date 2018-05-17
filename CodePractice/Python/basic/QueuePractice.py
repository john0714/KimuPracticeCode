# coding:utf-8
import time
import random

class Queue:
    def __init__(self):
        self.items = []

    def is_empty(self):
        return self.items == []
    #queueに入れる
    def enqueue(self, item):
        self.items.insert(0, item)
    #queueから出せる
    def dequeue(self):
        return self.items.pop()

    def size(self):
        return len(self.items)

def simulate_line(till_show, max_time):
    pq = Queue()
    tix_sold = []

    #enqueue Person(0~100)
    for i in range(100):
        pq.enqueue("person" + str(i))

    print(time.time())

    t_end = time.time() + till_show #終わる時間(time+till_show)(endtimeです)
    now = time.time() #現在時間
    while now < t_end and not pq.is_empty():
        now = time.time()
        r = random.randint(0, max_time) #randomで数を生成(0~max_timeの間)
        time.sleep(r) #スリープタイム
        person = pq.dequeue()
        print(person) #person出力
        tix_sold.append(person) #personを追加

    return tix_sold

sold = simulate_line(5, 1)
print(sold) #配列出力
# つまり、指定された時間の間、チケットを特定の時間の間申請するコードです。
