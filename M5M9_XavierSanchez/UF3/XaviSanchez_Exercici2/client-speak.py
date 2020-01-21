# Echo client program
import socket
import time

def rebre(socket):
    data=socket.recv(1024)
    print data
    pass

def enviar (socket):
    data =raw_input()
    socket.sendall(data)
    pass

if __name__ == "__main__":
    HOST = 'localhost'    # The remote host
    PORT = 50007              # The same port as used by the server
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((HOST,PORT))


    while True:
        
        enviar(s)
        rebre(s)

    a=s.recv(1024)
    print a
    s.close()

    pass
