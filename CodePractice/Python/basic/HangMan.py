# coding:utf-8
#関数 Hangman
import random

#random module使用
def selectword(Strings):
    return random.choice(Strings)

def hangman(word):
    wrong = 0
    stages = ["",
              "_________        ",
              "|                ",
              "|        |       ",
              "|        O       ",
              "|       /|\      ",
              "|       / \      ",
              "|                "
              ]
    rletters = list(word)   #問題文字数(list)
    board = ["_"] * len(word) #wordの文字数だけ_を作る
    win = False
    print("ハングマンへようこそ！")
    while wrong < len(stages) -1: #間違った数がstageより下ならゲームを続く
        msg = "1文字を予想してね"
        char = input(msg) #答え文字一つ入力, inputなのでAtomでは出来ません! Terminal使いましょう
        if char in rletters: #答え文字が問題文字に入っていると
            cind = rletters.index(char) #そこ文字の位置をチェックして
            board[cind] = char #boardを変えます
            rletters[cind] = '$' #当てる文字を$に変える
        else:
            wrong += 1
        print(" ".join(board))
        e = wrong + 1
        print("\n".join(stages[0:e]))#stagesチェック(スコアボード)
        if "_" not in board: #全部与えると
            print("あなたの勝ち！")
            print("答えは:" + " ".join(board))
            win = True
            break
        #ループ終了
    if not win:
        print("あなたの負け！答えは{}".format(word)+"です！")#正解表示


Strings = ["python", "ruby", "java", "dog", "car", "students", "terminal"]
hangman(selectword(Strings))
