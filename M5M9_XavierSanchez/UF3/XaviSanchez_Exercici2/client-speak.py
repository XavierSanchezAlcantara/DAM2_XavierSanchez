# Echo client program
import socket
import time
import threading
HOST = 'localhost'    # The remote host
PORT = 50010              # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((HOST,PORT))
data=""
def rebre(s):
    while True:
        data=s.recv(1024)
        print data

        if data=="bye":
            s.sendall(data) 
            break       

def enviar (s,data):
    while True:
        data =raw_input()
        if data:
            s.sendall(data)
   
        if data =="bye":
            s.sendall(data)
            break
            


fil = threading.Thread(target = rebre, args=(s,))
fil2 = threading.Thread(target = enviar, args=(s,data))
fil2.daemon= True
fil.start()
fil2.start() 
fil.join()  

s.close()

