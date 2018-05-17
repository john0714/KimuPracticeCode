class C1(object):
    def method1(self): return 'm1'
c1 = C1()
print(c1, c1.method1())

class C3(C1): #C3 inheritance by C1, C1을 상속받아서 C1의 기능 사용가능
    def method2(self): return 'm2'
c3 = C3()
print(c3, c3.method1())
print(c3, c3.method2())

class C2(object): #즉, 지금까지 Class옆의 괄호에 object라고 쓴건, 최상위 클래스인 object를 상속 받는다는것을 의미한다는 것이다
    def method1(self): return 'm1'
    def method2(self): return 'm2'
c2 = C2()
print(c2, c2.method1())
print(c2, c2.method2())