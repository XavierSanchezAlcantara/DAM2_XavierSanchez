
from http.server import HTTPServer, BaseHTTPRequestHandler

class Serv(BaseHTTPRequestHandler):    
    def do_GET(self):
        if self.path == '/practica.html':
                file_to_open = open(self.path[1:]).read()
                self.send_response(200)

        else:
            self.send_error(404)
            



        self.end_headers()
        self.wfile.write(bytes(file_to_open, 'utf-8'))

httpd = HTTPServer(('localhost', 8080), Serv)
httpd.serve_forever()
