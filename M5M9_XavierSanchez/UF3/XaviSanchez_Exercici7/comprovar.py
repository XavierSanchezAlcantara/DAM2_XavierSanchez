import urlparse
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import smtplib, ssl
password = "DollarDuck123"
context = ssl.create_default_context()

class GetHandler(BaseHTTPRequestHandler):

    def do_GET(self):
        if "practica.html" in self.path:
            file = open("practica.html", 'rb')
            self.send_response(200)
            self.send_header("Content-type", "text/html")
            self.end_headers()
            self.wfile.write(file.read())
        elif "foto.png" in self.path:
            file = open("foto.png", 'rb')
            self.send_response(200)
            self.send_header("Content-type", "image/png")
            self.end_headers()
            self.wfile.write(file.read())
            #CORREU
            mail = smtplib.SMTP_SSL("smtp.gmail.com", 465) 
            mail.ehlo() #missatge de salutació amb el servei/servidor
            mail.login("enterprisedollarduck@gmail.com", password) #login to gmail smtp
            message = "Subject: Hello There!" + "\n\n\nHa intentat entrar: %s" % str(self.client_address) #Cos del missatge
            mail.sendmail("enterprisedollarduck@gmail.com", "enterprisedollarduck@gmail.com", message) #envia el missatge
            mail.quit() 
        else:
            self.send_error(404, "Not Found")


if __name__ == '__main__':
    server = HTTPServer(('localhost', 8080), GetHandler)

    server.serve_forever()
