class C(object):
    def __init__(self, v):
        self.value = v
    def show(self):
        print(self.value)

    def getValue(self):
        return self.value
    def setValue(self, v):
        self.value = v

c1 = C(10)
print(c1.getValue())
c1.setValue(20) #インスタンス変数(クラス)の中に変数入力
print(c1.getValue())

#인스턴스 변수를 읽고 쓰기 위해 권장되는 방식 getter setter