#-*- coding: utf-8 -*-

"""
Modul per printar els numeros parells 
"""
class llista_primers:
    def __init__(self, n):
        self.n = n
        self.llista = []
        self.busca()

    def busca(self):
        """
        Metode per buscar el numero i veure si es parell
        """
        if (len(self.llista) == 0):
            # Llargaria llista es igual a 0 
            self.llista.append(2)
            self.busca()
        elif (len(self.llista) < self.n):
            # Llargaria llista es menor al numero introduit
            trobat = False
            seguent = self.llista[-1]+1
            while not trobat:
                for i in self.llista:
                    #recorre tots els numeros de la llista
                    if seguent%i == 0:
                        #Saber si el numero es par o impar, sabent si el residu es 0 o 1.
                        seguent += 1
                        trobat = False
                        break
                    else:
                        trobat = True
            
            self.llista.append(seguent)
            self.busca()
            #S'introdueix el seguent valor a la llista i executa la funcio busca de nou


if __name__ == '__main__':
    l = llista_primers(int(sys.argv[1]))
    # el usuari te que introduir un parametre si o si
    print l.llista