import telebot
import requests
import json
import os
import sys

from random import randint
reload(sys)
sys.setdefaultencoding('utf-8')
fitxer_no_us=True
bot = telebot.TeleBot("940457893:AAH6J8fRSRMrMlMbm5zbYdjeliz51dAK944")


global var1,var2  
@bot.message_handler(commands=['start'])
def start(message):
    bot.reply_to(message, "Tens que seleccionar una d'aquestes tres opcions:  /play, /help ")
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

@bot.message_handler(commands=['play'])
def send_play(message):
    bot.reply_to(message, "Selecciona una de les opcions")
    bot.reply_to(message, "Pedra= /pedra, Paper= /paper, Tisora= /tisor")

@bot.message_handler(commands=['pedra'])
def send_joc(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)

    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Empatat")
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat")
        print 'win'
        win(message.chat.id)
        

    else:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut")
        print 'lose'
        lose(message.chat.id)
        
        pass
    

    pass
@bot.message_handler(commands=['paper'])
def send_joc1(message):
    llista=['pedra','paper','tijera']

    a=randint(0,2)
    if a==0:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat")
        win(message.chat.id)
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Perdut")
        lose(message.chat.id)
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
        lose(message.chat.id)
    elif a==2:
        bot.send_message(message.chat.id, llista[a])
        bot.send_message(message.chat.id, "Has Guanyat")
        win(message.chat.id)
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

"""
def fitxer_en_us():
    if fitxer_no_us:
        fitxer_no_us=False
        fitxer_no_us=True

    else:
        print()
"""
def win(usuari):
    fr= open("./historial.txt","r+")
    fr.seek(0)
    for x in fr.readlines() :
        print x
        if (str(usuari)) in x:
            lose = int(x.split(' ')[2])
            print fr.tell()
            fr.write(str(usuari)+' '+x.split(' ')[1]+' '+str(lose+1)+'\n')
            break
    fr.close()  
def lose(usuari):
    print 'dins lose'
    fr= open("./historial.txt","r+")
    fr.seek(0)
    rt = fr.read()
    pos = rt.find(str(usuari))
    pos2 = rt.find('\n')
    print pos+len(str(usuari)), pos2
    print rt[:13]
    new = rt[13:]
    for x in fr.readlines() :
        print x
        if (str(usuari)) in x:
            lose = int(x.split(' ')[2])
            print fr.tell()
            str(usuari).replace(x.split(' ')[1],2)
            fr.write(str(usuari)+' '+x.split(' ')[1]+' '+str(lose+1)+'\n')
            break
    fr.close()  

bot.polling()