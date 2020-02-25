
# Echo server program
import socket
import time
import threading
HOST = 'localhost'                 # Symbolic name meaning all available interfaces
PORT = 50009             # Arbitrary non-privileged port
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((HOST,PORT))
s.listen(3)
lista = []

def acceptace(s):
    while True:
        #acceptem la conexio
        conn, addr = s.accept()
        t = threading.Thread(target=recibir, args=(conn, lista))
        t.start()
       


def recibir(conexion, lista):
    nom=""
    while True:
        #recibir mensaje
            
        if  nom=="":a
            nom=conexion.recv(1024)
            lista.append((conexion,nom[:-1]))
            print "nom",nom
        else:
            data = conexion.recv(1024)
            print data
            thread2 = threading.Thread(target=enviar, args=(conexion, nom, data))
            thread2.start()
            if data == "bye\n":
                print "entro a bye"
                conexion.close()
                lista.remove(conexion)
                print lista
                break


    
    
def enviar(conexion, nom, data):
       
    for x in lista:
        if x[0] == conexion:
            pass
        else:    
            x[0].sendall(nom[:-1]+":"+data)

        
        
thread1 = threading.Thread(target=acceptace, args=(s,))
thread1.start()





thread1.join()
s.close()