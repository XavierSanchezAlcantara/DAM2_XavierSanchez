# Echo client program
import socket

HOST = 'localhost'    # The remote host
PORT = 50007              # The same port as used by the server
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
while True:
    print("Introdueix informacio")
    dato = raw_input()

    s.sendto(dato, (HOST,PORT))
    if dato in "bye":
        time.sleep(1)
        break
s.close()
