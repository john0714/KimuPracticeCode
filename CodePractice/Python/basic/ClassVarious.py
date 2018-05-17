class Cs:
    count = 0 #Python에선 클래스 안, 메소드 밖의 변수는 클래스 소속의 변수가 된다.
    def __init__(self): #InstanceMethod에선 첫번째 인자가 Instance를 가리킴
        Cs.count = Cs.count + 1
    @classmethod
    def getCount(cls):  #classmethod또한 첫번째 인자가 꼭 들어가며, 그 인자는 그 Method를 담고 있는 Class를 가리킨다.
        return cls.count #메소드 안에서 클래스 변수를 쓰려면 [클래스명.변수]형태로 접근해야함(전역 변수 개념이 없는듯?)
i1 = Cs()
i2 = Cs()
i3 = Cs()
i4 = Cs()
print(Cs.getCount())