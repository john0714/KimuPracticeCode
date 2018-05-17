class C(object):
    def __init__(self, v):
        self.__value = v
    def show(self):
        print(self.__value)

c1 = C(10)
#print(c1.__value)
c1.show()
#외부에서 접근 가능한 변수를 파이썬은 property, 루비는 attirbute라고 함
#Python에선 원래 getset없이 인스턴스 변수값에 직접 접근이 가능하지만,
#init에서 __value로 설정 해두면 직접 접근이 안되게 만들 수 있음(인스턴스 변수 안의 함수 사용도 안되게 됨)