class Cal(object):
    _history = [] #_는 외부에서 쓰지 않고 내부에서만 쓰는 변수라는 의미
    def __init__(self, v1, v2):
        if isinstance(v1, int):
            self.v1 = v1
        if isinstance(v2, int):
            self.v2 = v2
    def add(self):
        result = self.v1+self.v2
        Cal._history.append("add : %d+%d=%d" % (self.v1, self.v2, result)) #계산 할 때 마다 history배열에 값을 넣음
        return result
    def subtract(self):
        result = self.v1-self.v2
        Cal._history.append("subtract : %d-%d=%d" % (self.v1, self.v2, result))
        return result
    def setV1(self, v):
        if isinstance(v, int):
            self.v1 = v
    def getV1(self):
        return self.v1
    @classmethod
    def history(cls):
        for item in Cal._history:
            print(item)

class CalMultiply(Cal): #Cal을 상속받음
    def multiply(self):
        result = self.v1*self.v2
        Cal._history.append("multiply : %d*%d=%d" % (self.v1, self.v2, result))
        return result
class CalDivide(CalMultiply): #CalMultiply를 상속받음
    def divide(self):
        result = self.v1/self.v2
        Cal._history.append("divide : %d/%d=%d" % (self.v1, self.v2, result))
        return result

c1 = CalMultiply(10,10)
print(c1.add())
print(c1.multiply())
c2 = CalDivide(20,10)
print(c2, c2.add()) #상속에 상속 받은 내용도 사용 가능
print(c2, c2.multiply())
print(c2, c2.divide())
Cal.history() #Cal클래스 안의 history함수 실행