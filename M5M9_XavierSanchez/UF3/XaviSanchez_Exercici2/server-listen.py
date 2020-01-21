# Echo server program
import socket
import time
import threading


def rebre(socket):
    data=socket.recv(1024)
    print data
    pass

def enviar (socket):
    socket.sendall(data)
    pass


if __name__ == "__main__":
    HOST = ''                 # Symbolic name meaning all available interfaces
    PORT = 50007              # Arbitrary non-privileged port
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((HOST,PORT))
    s.listen(1)
    conn,addr = s.accept()
    data=""
    fil = threading.Thread(target = enviar, args=(conn,data))
    fil2 = threading.Thread(target = rebre, args=(conn,))
    fil.start()
    fil2.start()
    while True:
        data =raw_input()
        
        fil.join()
        fil2.join()
        

    s.close()

    pass