from multiprocessing import *

import time
#a=Value('i',3)
def t(s):
    while True:
        time.strftime("%H:%M:%S") 
        print(time.strftime("%H:%M:%S"))
        time.sleep(s)
 #       s= a.value
def main():
    i=0
    
    p = Process (target=t, args=(1 , ))
    p.start()
    for i in range(1,10):
       
        print(p.pid)
        time.sleep(0.5)
#        a.value=2
        pass
    p.terminate()
    print("fi")


    
    


main()