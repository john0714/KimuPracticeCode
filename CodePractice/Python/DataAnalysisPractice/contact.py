# -*- coding: utf-8 -*-
# Run : shift + F10

# ===============정보 보기, 입력, 출력, 삭제 함수 및 클래스=================
class Contact:
    def __init__(self, name, phone_number, e_mail, addr):
        self.name = name
        self.phone_number = phone_number
        self.e_mail = e_mail
        self.addr = addr

    # 연락처 출력 받기(input)
    def print_info(self):
        print("Name: ", self.name)
        print("Phone Number: ", self.phone_number)
        print("E-mail: ", self.e_mail)
        print("Address: ", self.addr)


# 연락처 입력 받기(input)
def set_contact():
    name = input("Name: ")
    phone_number = input("Phone Number: ")
    e_mail = input("E-mail: ")
    addr = input("Address: ")
    contact = Contact(name, phone_number, e_mail, addr)
    return contact  # return으로 contact instance값 넘김


# 메뉴
def print_menu():
    print("1. 연락처 입력")
    print("2. 연락처 출력")
    print("3. 연락처 삭제")
    print("4. 종료")
    menu = input("메뉴선택: ")
    return int(menu)


def print_contact(contact_list):
    for contact in contact_list:
        contact.print_info()


def delete_contact(contact_list, name):
    for i, contact in enumerate(contact_list):
        if contact.name == name:
            del contact_list[i]  # i에는 숫자가 자동으로 들어가서 증가함


# ===============정보 보기, 입력, 출력, 삭제 함수 및 클래스 종료=================


# 파일 저장 함수
def store_contact(contact_list):
    f = open("contact_db.txt", "wt")  # FileOpen, 권한은 쓰기
    for contact in contact_list:
        f.write(contact.name + '\n')
        f.write(contact.phone_number + '\n')
        f.write(contact.e_mail + '\n')
        f.write(contact.addr + '\n')
    f.close()  # 저장 -> 프로젝트 경로에 저장됨


# 파일 불러들이기 함수
def load_contact(contact_list):
    f = open("contact_db.txt", "rt")  # FileOpen, 권한은 읽기
    lines = f.readlines()
    num = len(lines) / 4  # 4줄씩 끊어읽음
    num = int(num)  # 형변환

    for i in range(num):
        name = lines[4 * i].rstrip('\n')  # \n제외하고 부름, 4의배수(0부터 시작)
        phone = lines[4 * i + 1].rstrip('\n')  # 이하 동일
        email = lines[4 * i + 2].rstrip('\n')
        addr = lines[4 * i + 3].rstrip('\n')
        contact = Contact(name, phone, email, addr)
        contact_list.append(contact)
    f.close()


def run():
    contact_list = []  # list생성
    load_contact(contact_list)  # 프로그램 시작시 불러옴
    while 1:
        menu = print_menu()
        if menu == 1:
            contact = set_contact()  # return으로 contact값 받아서 입력
            contact_list.append(contact)  # 생성한 리스트에 contact 입력
        if menu == 2:
            print_contact(contact_list)
        if menu == 3:
            name = input("삭제할 이름을 적어주세요: ")
            delete_contact(contact_list, name)
        if menu == 4:
            store_contact(contact_list)  # 프로그램 종료시 저장함수 부름
            break
    # set_contact()


if __name__ == "__main__":
    run()
