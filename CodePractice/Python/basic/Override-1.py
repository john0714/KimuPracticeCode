#재정의, 상속받은 자식객체에서 부모객체의 기능을 재정의 해서 사용하는걸 뜻함

class C1:
    def m(self):
        return 'parent'
class C2(C1): #상속
    def m(self): #Override(supermethod사용하여 재정의)
        return super().m() + 'child' #C1에서 설정한 m() 메소드의 뒤에child를 붙여서 다시 C2에서 m()메소드를 정의함
    pass
o = C2()
print(o.m())