#-*- coding: utf8 -*-
#4523

# 40 / 2 = 20
# 40 / 4 = 10
from multiprocessing import Pool
from datetime import datetime

def primers(num):
    for i in range(2, num/2):
        if num % i == 0:
            return False
        else:
            pass
    return True

if __name__ == '__main__':
    start = datetime.now()
    p = Pool(8)
    a=0
    l= range(40000000, 40000100)
    h=p.map(primers,l)
    
    for i in l:
        print i,h[a]
        a=a+1
    print datetime.now() - start
    p
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    .close()
