window.onload = function() {

    visor=document.getElementById("reloj");
    divison=document.getElementById("cronometro");

    document.cron.boton1.onclick = activo; 
    document.cron.boton2.onclick = pausa;
    document.cron.boton3.onclick = pausa;
    document.cron.boton1.disabled=false;
    document.cron.boton2.disabled=true;
    document.cron.boton3.disabled=true;
    
    var marcha=0;
    var cro=0;
    var i=0;
    var fraccio=" ";
    }
    

    function activo (){   
         if (document.cron.boton1.value=="Empezar") { 
            empezar() 
            }
         else {  
            reiniciar() 
            }
         }
    
    function pausa (){ 
         if (marcha==0) { 
            continuar() 
            }
         else {  
            parar() 
            }
         }
  
    function empezar() {
          emp=new Date() 
          elcrono=setInterval(tiempo,10); 
          marcha=1 
          document.cron.boton1.value="Reinicia"; 
          document.cron.boton2.disabled=false; 
          document.cron.boton3.disabled=false; 
          }		
    function tiempo() { 
         actual=new Date(); 
          
         cro=actual-emp; 
         cr=new Date(); 
         cr.setTime(cro); 
        
         cs=cr.getMilliseconds(); 
         cs=cs/10; 
         cs=Math.round(cs); 
         sg=cr.getSeconds(); 
         mn=cr.getMinutes(); 
         ho=cr.getHours()-1;  
         if (cs<10) {cs="0"+cs;} 
         if (sg<10) {sg="0"+sg;} 
         if (mn<10) {mn="0"+mn;} 
         visor.innerHTML=ho+" "+mn+" "+sg+" "+cs; 
         }
    function Fraccio(){
        fraccio+=elcrono+", ";
        divison.innerHTML=fraccio;
    }
   
    function parar() { 
         clearInterval(elcrono); 
         marcha=0; 
         document.cron.boton2.value="Continuar"; 
         }		 
    function continuar() {
         emp2=new Date(); 
         emp2=emp2.getTime(); 
         emp3=emp2-cro; 
         emp=new Date();  
         emp.setTime(emp3); 
         elcrono=setInterval(tiempo,10); 
         marcha=1; 
         document.cron.boton2.value="parar"; 
         document.cron.boton1.disabled=false; 
         }
    function reiniciar() {
         if (marcha==1) { 
             clearInterval(elcrono); 
             marcha=0;	
             }
                
         cro=0; 
         visor.innerHTML = "0 00 00 00"; 
         document.cron.boton1.value="Empezar"; 
         document.cron.boton2.value="Parar"; 
         document.cron.boton2.disabled=true;  	 
         }	
    