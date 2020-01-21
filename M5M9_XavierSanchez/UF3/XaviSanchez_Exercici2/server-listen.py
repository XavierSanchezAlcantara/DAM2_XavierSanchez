# Echo server program
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
    HOST = ''                 # Symbolic name meaning all available interfaces
    PORT = 50007              # Arbitrary non-privileged port
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.bind((HOST,PORT))
    s.listen(1)
    conn,addr = s.accept()


    while True:
        rebre(conn)
        enviar(conn)
    s.close()

    pass