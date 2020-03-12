from Tkinter import *
from socket import *
import urllib
import re
import base64
import pygame
#import win32gui

HOST = 'localhost'
PORT = 5011


def getmixerargs():
    pygame.mixer.init()
    freq, size, chan = pygame.mixer.get_init()
    return freq, size, chan
def initMixer():
    BUFFER = 3072  # audio buffer size, number of samples since pygame 1.8.
    FREQ, SIZE, CHAN = getmixerargs()
    pygame.mixer.init(FREQ, SIZE, CHAN, BUFFER)
def playsound(soundfile):
    """Play sound through default mixer channel in blocking manner.
       This will load the whole sound into memory before playback
    """
    pygame.init()
    pygame.mixer.init()
    sound = pygame.mixer.Sound(soundfile)
    clock = pygame.time.Clock()
    sound.play()
    while pygame.mixer.get_busy():
        clock.tick(1000)
def playmusic(soundfile):
    """Stream music with mixer.music module in blocking manner.
       This will stream the sound from disk while playing.
    """
    pygame.init()
    pygame.mixer.init()
    clock = pygame.time.Clock()
    pygame.mixer.music.load(soundfile)
    pygame.mixer.music.play()
    while pygame.mixer.music.get_busy():
        clock.tick(1000)
def stopmusic():
    """stop currently playing music"""
    pygame.mixer.music.stop()

#HOW TO PLAY SONG:
initMixer()
#playmusic(filename)


def FlashMyWindow(title):
    pass
    #ID = win32gui.FindWindow(None, title)
    #win32gui.FlashWindow(ID,True)

def FlashMyWindow2(title2):
    ID2 = win32gui.FindWindow(None, title2)
    win32gui.FlashWindow(ID2,True)

def GetExternalIP():
    url = "http://checkip.dyndns.org"
    request = urllib.urlopen(url).read()
    return str(re.findall(r"\d{1,3}\.\d{1,3}\.\d{1,3}.\d{1,3}", request))

def GetInternalIP():
    return str(gethostbyname(getfqdn()))

def FilteredMessage(EntryText):
    """
    Filter out all useless white lines at the end of a string,
    returns a new, beautifully filtered string.
    """
    EndFiltered = ''
    for i in range(len(EntryText)-1,-1,-1):
        if EntryText[i]!='\n':
            EndFiltered = EntryText[0:i+1]
            break
    for i in range(0,len(EndFiltered), 1):
            if EndFiltered[i] != "\n":
                    return EndFiltered[i:]+'\n'
    return ''

def LoadConnectionInfo(ChatLog, EntryText):
    if EntryText != '':
        ChatLog.config(state=NORMAL)
        if ChatLog.index('end') != None:
            ChatLog.insert(END, EntryText+'\n')
            ChatLog.config(state=DISABLED)
            ChatLog.yview(END)

def LoadMyEntry(ChatLog, EntryText):
    if EntryText != '':
        ChatLog.config(state=NORMAL)
        if ChatLog.index('end') != None:
            LineNumber = float(ChatLog.index('end'))-1.0
            ChatLog.insert(END, "You: " + EntryText)
            ChatLog.tag_add("You", LineNumber, LineNumber+0.4)
            ChatLog.tag_config("You", foreground="#FF8000", font=("Arial", 12, "bold"))
            ChatLog.config(state=DISABLED)
            ChatLog.yview(END)


def LoadOtherEntry(ChatLog, EntryText):
    if EntryText != '':
        ChatLog.config(state=NORMAL)
        if ChatLog.index('end') != None:
            try:
                LineNumber = float(ChatLog.index('end'))-1.0
            except:
                pass
            text = EntryText.split(':',1)
            ChatLog.insert(END, text[0] + ": " + text[1])
            ChatLog.tag_add(text[0] + ": ", LineNumber, LineNumber+float('0.' + str(len(text[0] + ":"))))
            ChatLog.tag_config(text[0] + ": ", foreground="#04B404", font=("Arial", 12, "bold"))
            ChatLog.config(state=DISABLED)
            ChatLog.yview(END)

def ReceiveImage(data,s):
    print "Entra a ReceiveImage"
    path = "/home/xavi2/Escriptori/Gitub/DAM2_XavierSanchez/M5M9_XavierSanchez/UF3/XaviSanchez_Exercici5"
    dtime = datetime.datetime.now()
    basename = "img"+str(dtime)+".png"
    print "basename ---------->"+basename

    img = open( path + basename,'wb')
    text = data.split() # /image
    size = text[-1]
    print "text -------------->" + text[0] + " " + text[1]
    r_size = 0

    t = s.recv(40960000) # Recibir c_data de la imagen (user: /image c_data)

    print "t ------------------>" + t
    c_data = t.split(' ') # Separar el /image del cdata
    print "c_data ------------->" +c_data[0] + " " + c_data[1]
    r_size += len(c_data[-1])

    while r_size < int(size):
        print "COMPROVADOR " + size,len(c_data)
        t2 = s.recv(40960000)
        c_data[-1] += t2
        r_size += len(t2)

    img.write(c_data[-1].decode('base64'))
    img.close()
    print "Guardado"

    return "/image "+ str(path+basename)

def SendImage(EntryText,s):


    if "\n" in EntryText:
        EntryText = EntryText[:-1]

    text = EntryText.split()
    print "Entry text -->" + EntryText
    print "text[1] -->" +text[0]



    img = open(text[1],'rb') # Abrir la imagen
    data = img.read()
    c_data = data.encode('base64') # data codificado
    size = len(c_data)
    print "Data------------------>" + data
    print "Size------------------>" + str(size)
    print "c_data---------------->" + c_data
    s.sendall('/image ' + str(size))
    time.sleep(1)
    s.sendall('/image ' + c_data)
    print "Enviado"