class Cs:
    @staticmethod
    def static_method():
        print("Static method")
    #staticmethod와 classmethod는 class자체에 소속 되어있는 메소드이다.(@를 붙여서 구분함)
    @classmethod
    def class_method(cls):
        print("Class method")
    def instance_method(self):
        print("Instance method")
    #instancemethod는 인스턴스에 소속되어있는 메소드이다(self로 인스턴스를 불러와서 그대로 사용함)
i = Cs() #인스턴스 변수생성
Cs.static_method() #StaticMethod는 Class소속이므로 Class에서 바로 사용함
Cs.class_method() #ClassMethod는 Class소속이므로 Class에서 바로 사용함
i.instance_method() #InstanceMethod는 Instance소속이므로 Instance변수를 생성해서 사용함