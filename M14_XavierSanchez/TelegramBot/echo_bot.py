import telebot
import requests
import json
import os
import sys
from threading import Semaphore
from random import randint

reload(sys)
sys.setdefaultencoding('utf-8')
fitxer_no_us=True
bot = telebot.TeleBot("940457893:AAH6J8fRSRMrMlMbm5zbYdjeliz51dAK944")
s=Semaphore(1)
json_keyboard_start=json.dumps({'keyboard':[["Start"],["Help"]],'one_time_keyboard':True, 'resize_keyboard':True})
json_keyboard=json.dumps({'keyboard':[["/play"],["/historial"],["/help"]],'one_time_keyboard':False, 'resize_keyboard':True})
json_keyboard_jugar=json.dumps({'keyboard':[["/pedra"],["/paper"],["/tisora"],["/enrere"]],'one_time_keyboard':False, 'resize_keyboard':True})
 
@bot.message_handler(commands=['start'])
def start(message):
    bot.reply_to(message,"Empieza el juego \n Pedra,Paper,Tisora \n Que vols fer: jugar? \n Veure el historial? \n O necessites ajuda?",reply_markup=json_keyboard)
    f = open("historial.txt", "r+") 
    f.seek(0)
    if (str(message.chat.id)) not in f.read():
        print(message.chat.id)
        f.write(str(message.chat.id)+' 0 0\n')
        print f
        f.close()
    else:
        
        
        print("en el fixero")
        f.close()
        pass

@bot.message_handler(commands=['help'])
def send_help(message):
    bot.reply_to(message, "Tens que seleccionar una d'aquestes tres opcions: /pedra, /paper, /tisora ")

@bot.message_handler(commands=['enrere'])
def enrere(message):
    bot.reply_to(message,"hola",reply_markup=json_keyboard)
@bot.message_handler(commands=['play'])
def send_play(message):
    bot.reply_to(message, "Selecciona una de les opcions")
    bot.reply_to(message, "Pedra=/pedra, Paper= /paper, Tisora= /tisora",reply_markup=json_keyboard_jugar)

@bot.message_handler(commands=['pedra'])
def send_joc(message):
    llista=['pedra','paper','tisora']

    a=randint(0,2)

    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat",reply_markup=json_keyboard_jugar)
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat",reply_markup=json_keyboard_jugar)
        win(message.chat.id)
    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut",reply_markup=json_keyboard_jugar)
        lose(message.chat.id)
        pass
    

    pass
@bot.message_handler(commands=['paper'])
def send_joc1(message):
    llista=['pedra','paper','tisora']

    a=randint(0,2)
    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat",reply_markup=json_keyboard_jugar)
        win(message.chat.id)
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut",reply_markup=json_keyboard_jugar)
        lose(message.chat.id)
    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat",reply_markup=json_keyboard_jugar)
        pass
    

    pass
@bot.message_handler(commands=['tisora'])
def send_joc2(message):
    llista=['pedra','paper','tisora']

    a=randint(0,2)
    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut",reply_markup=json_keyboard_jugar)
        lose(message.chat.id)
    elif a==1:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat",reply_markup=json_keyboard_jugar)
        win(message.chat.id)
    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat",reply_markup=json_keyboard_jugar)
        pass
    
    pass

@bot.message_handler(commands=['historial'])
def send_history(message):
    f2= open("historial.txt", "r")

    fr=f2.read()
    f2.close()
    index=fr.find(str(message.chat.id))
    index2=fr.find('\n',index+1)
    text=fr[index:index2].split(" ")
    victoria=text[1]
    derrota=text[2]
    bot.send_message(message.chat.id, "Victories = "+victoria+" Derrota = "+derrota)
    pass

"""
def fitxer_en_us():
    if fitxer_no_us:
        fitxer_no_us=False
        fitxer_no_us=True

    else:
        print()
"""

def win(usuari):
    s.acquire()

    f = open('historial.txt', 'r')
    fr = f.read()
    f.close()
    print usuari
    index = fr.find(str(usuari))
    index2 = fr.find('\n', index+1)
    text=fr[index:index2].split(" ")
    print text
    victoria=int(text[1])
    derrota=int(text[2])
    victoria=victoria+1
    print fr[index:index2]
    f = open('historial.txt', 'w')
    f.write(fr[:index])
    f.write(str(usuari)+ ' '+str(victoria)+' '+str(derrota)+'\n')
    f.write(fr[index2+1:])
    f.close()
    s.release()
def lose(usuari):
    s.acquire()
    f = open('historial.txt', 'r')
    fr = f.read()
    f.close()
    print usuari
    index = fr.find(str(usuari))
    index2 = fr.find('\n', index+1)
    text=fr[index:index2].split(" ")
    print text
    victoria=int(text[1])
    derrota=int(text[2])
    derrota=derrota+1
    print fr[index:index2]
    print str(usuari)+ ' '+str(victoria)+' '+str(derrota)
    f = open('historial.txt', 'w')
    f.write(fr[:index])
    f.write(str(usuari)+ ' '+str(victoria)+' '+str(derrota)+'\n')
    f.write(fr[index2+1:])
    f.close()
    s.release()
@bot.message_handler(lambda):
bot.polling()