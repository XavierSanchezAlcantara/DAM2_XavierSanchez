import requests
import telebot
import random
bot = telebot.TeleBot("940457893:AAH6J8fRSRMrMlMbm5zbYdjeliz51dAK944")
@bot.message_handler(commands=['help'])
def send_welcome(message):
	bot.reply_to(message, "Selecciona ")

	print message
@bot.message_handler(commands=['ppt'])
def send_welcome(message):
	bot.reply_to(message, "Selecciona una de les opcions")
	bot.reply_to(message, "Pedra= /pedra, Paper= /paper, Tisora= /tisor")

	print message
@bot.message_handler(commands=['pedra'])
def send_joc(message):
	lista=['pedra','paper','tijera']

	a=random.randint(0,2)

	bot.reply_to(message, llista[a])

	pass
@bot.message_handler(commands=['paper'])
def send_joc(message):
		lista=['pedra','paper','tijera']

		a=random.randint(0,2)

		bot.reply_to(message, llista[a])

		pass
@bot.message_handler(commands=['tijera'])
def send_joc(message):
	lista=['pedra','paper','tijera']

	a=random.randint(0,2)

	bot.reply_to(message, message[a])

	pass

bot.polling()
