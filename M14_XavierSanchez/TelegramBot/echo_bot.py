import telebot
import requests
import json
import os
import sys

from random import randint
reload(sys)
sys.setdefaultencoding('utf-8')
bot = telebot.TeleBot("940457893:AAH6J8fRSRMrMlMbm5zbYdjeliz51dAK944")



@bot.message_handler(commands=['start'])
def start(message):
    bot.reply_to(message, "Tens que seleccionar una d'aquestes tres opcions:  /play, /help ")
    f = open("historial.txt", "a") 

    if (str(message.chat.id)) not in f:
        print(message.chat.id)
        f.close()
    else:
        f.write(str(message.chat.id)+"\n")
        f.close()
        pass

@bot.message_handler(commands=['help'])
def send_help(message):
    bot.reply_to(message, "Tens que seleccionar una d'aquestes tres opcions: /pedra, /paper, /tisora ")

@bot.message_handler(commands=['play'])
def send_play(message):
    bot.reply_to(message, "Selecciona una de les opcions")
    bot.reply_to(message, "Pedra= /pedra, Paper= /paper, Tisora= /tisor")

@bot.message_handler(commands=['pedra'])
def send_joc(message):
    llista=['pedra','paper','tijera'    ]

    a=randint(0,2)

    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat")
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat")
    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut")
        pass
    

    pass
@bot.message_handler(commands=['paper'])
def send_joc1(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)
    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat")
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut")
    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat")
        pass
    

    pass
@bot.message_handler(commands=['tijera'])
def send_joc2(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)
    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.reply_to(message.chat.id, "Has Perdut")
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat")
    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat")
        pass
    
    pass
@bot.message_handler(commands=['historial'])
def send_history(message):
    f2= open("historial.txt", "r")

    bot.send_message(message.chat.id, f2.read())
    f2.close()
    pass

bot.polling()