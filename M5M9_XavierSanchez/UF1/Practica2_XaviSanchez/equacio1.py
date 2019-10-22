
class EquacioPrimerGrau:
    def __init__(self,eq):
        self.eq = eq
        
        #split=eq.split()
        a,self.operacio,b,igual,c=eq.split()
        x=a[:-1]
        z=a[-1]
        
        self.aa=int(x)
        self.bb=int(b)
        self.cc=int(c)
        print("Part12= "+x)
        print("Part1 = "+a)
        print("Part2 = "+b)
        print("Operador= "+ self.operacio)
        print("Part3 = "+c)
    def calcula(self):    

        if self.operacio=="+":
            resultat=(self.cc-self.bb)/self.aa
        else:
            resultat=(self.cc+self.bb)/self.aa
        
        print(resultat)




#extreuPart1asasda2()

#extreuPart1()

##ExtreuPart2()

#ExtreuOperador()

#ExtreuPart3()