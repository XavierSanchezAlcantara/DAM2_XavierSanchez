class EquacioPrimerGrau:


    def __init__(self,equacion):

        self.equacion = equacion.split()
        

    def calcula(self):
        part12 = self.equacion[0]
        try:
            part1 = float(part12[:-1])
            part3 = float(self.equacion[2])
            part4 = float(self.equacion[4])
        except: 
            return("l'equacio te un caracter no calculable o no segueix el format: ax + b = c")
        part2 = part12[1]
        operador = self.equacion[1]
        
        #print ("part1=",part1)
        #print ("part2=",part2)
        #print ("operator=",operador)
        #print ("part3=",part3)  
        #print ("part4=",part4) 


        
        if operador == "+":
            calculo = (part4 - part3) / part1
            return calculo

            
        elif operador == "-":
            calculo = (part4 + part3) / part1
            return calculo

        else: 
           return ("Operador no valid: ")
            
        


#extreuPart1asasda2()

#extreuPart1()

##ExtreuPart2()

#ExtreuOperador()

#ExtreuPart3()