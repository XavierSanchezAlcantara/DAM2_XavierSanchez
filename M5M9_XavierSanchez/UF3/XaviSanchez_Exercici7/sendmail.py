import os
import datetime
import urllib
import smtplib
import re, urllib2
import urllib.request
def send_email():
    lista = "0123456789."
    ip=""
	dato=urllib.request.urlopen("http://checkip.dyndns.org").read()
	for x in str(dato):

		if x in lista:

			ip += x

	server = smtplib.SMTP('smtp.gmail.com',587)
    server.ehlo()
    server.starttls()
    server.ehlo()
    server.login('xavisnchez28@gmail.com', "Perrolila9")
    message = 'sending this from python!'+InfoSistema.GetIpPublica()
    server.sendmail('xavisnchez28@gmail.com', 'enterprisedollarduck@gmail.com ', message)
    server.quit()
