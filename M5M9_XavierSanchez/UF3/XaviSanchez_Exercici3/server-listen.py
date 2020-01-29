
# Echo server program
import socket
import time
import threading
HOST = 'localhost'                 # Symbolic name meaning all available interfaces
PORT = 50010              # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST,PORT))
s.listen()
conn,addr = s.accept()
data=""
def rebre(conn):
    while True:
        
        data=conn.recv(1024)
        print data

        if data=="bye":
            conn.sendall(data) 
            break       

def enviar (conn,data):
    while True:
        data =raw_input()
        if data:
            conn.sendall(data)
            
   
        if data =="bye":
            conn.sendall(data)
            break

    


fil = threading.Thread(target = rebre, args=(conn,))
fil2 = threading.Thread(target = enviar, args=(conn,data))
fil2.daemon= True
fil.start()
fil2.start()
fil.join()
s.close()
    
        

