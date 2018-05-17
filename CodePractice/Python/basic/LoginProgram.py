# -*- coding: utf-8 -*-

import auth

input_id = input("아이디를 입력해주세요.\n")

if auth.login(input_id):
    print('Hello, '+input_id)
else:
    print('Who are you?')

# 남에게 알려주는것 또한 두번 배우는 것이다.
# 그래도 나는 그냥 혼자 하는게 편하다