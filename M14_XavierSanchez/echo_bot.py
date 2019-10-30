import telebot
import requests
import json
from random import randint

bot = telebot.TeleBot("940457893:AAH6J8fRSRMrMlMbm5zbYdjeliz51dAK944")
f = open("historial.txt", "a") 
keyboard_inici = json.dumps({'keyboard': [["play"],["Help"]], 'one_time_keyboard':True,'resize_keyboard':True})

keyboard_opcions = json.dumps({'keyboard':[["Pedra"],["Paper"],["Tisora"]],'one_time_keyboard':True,'resize_keyboard':True})
@bot.message_handler(commands=['help'])
def send_help(message):
    bot.reply_to(message, "Tens que seleccionar una d'aquestes tres opcions: /pedra, /paper, /tisora ",keyboard_opcions)

@bot.message_handler(commands=['play'])
def send_play(message):
    bot.reply_to(message, "Selecciona una de les opcions")
    bot.reply_to(message, "Pedra= /pedra, Paper= /paper, Tisora= /tisor")

@bot.message_handler(commands=['pedra'])
def send_joc(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)

    if a==0:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Empatat")
    elif a==2:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Guanyat")
    else:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Perdut")
        pass
    

    pass
@bot.message_handler(commands=['paper'])
def send_joc1(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)
    if a==0:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Guanyat")
    elif a==2:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Perdut")
    else:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Empatat")
        pass
    

    pass
@bot.message_handler(commands=['tijera'])
def send_joc2(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)
    if a==0:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Perdut")
    elif a==2:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Guanyat")
    else:
        bot.reply_to(message, llista[a])
        bot.reply_to(message, "Has Empatat")
        pass
    
    pass
@bot.message_handler(commands=['historial'])
def send_history(message):
    f2= open("historial.txt", "r")

    bot.reply_to(message, f2.read())
    f2.close()
    pass

bot.polling()