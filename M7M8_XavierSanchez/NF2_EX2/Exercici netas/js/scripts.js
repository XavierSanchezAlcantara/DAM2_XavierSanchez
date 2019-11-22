$(document).ready(function(){
    $(".boton").click(function(){
        $("#formulari").slideToggle();
        
       
     });

    
    //var newsArray = JSON.parse(localStorage.getItem('myArray'))

    if(localStorage.myArray){
        var newsArray = JSON.parse(localStorage.getItem('myArray'));
    }else {
        var newsArray = [];
    }



    for (var i = 0; i <= newsArray.length; i++){

        $(newsArray[i].noticia.n).prepend(newsArray[i].noticia.a);

    }

    $("#botonNoticia").click(function(){
        
        var d = new Date();
        //var newsArray = [$("#titulo").val(),$("#textArea").val()];
        var noticia=new Object();
        if ($('#select').val()=="Secundari"){

            $("section").prepend("<article>"+"<h2>"+$("#titulo").val()+"<button class= 'fa fa-trash fa-lg'></button></h2><p>"+$("#textArea").val()+"</p>"+d.getDate()+"-"+d.getMonth()+"-"+d.getFullYear()+"</article>");
            noticia.n= $("section")
            noticia.a="<article>"+"<h2>"+$("#titulo").val()+"<button class= 'fa fa-trash fa-lg'></button></h2><p>"+$("#textArea").val()+"</p>"+d.getDate()+"-"+d.getMonth()+"-"+d.getFullYear()+"</article>"
            newsArray.push(noticia);
        }else if ($('#select').val() == "Principal"){
            $("aside").prepend("<article>"+"<h3>"+$("#titulo").val()+"<button class= 'fa fa-trash fa-lg'></button></h3><p>"+$("#textArea").val()+"</p>"+d.getDate()+"-"+d.getMonth()+"-"+d.getFullYear()+"</article>");
            noticia.n= '$("aside")'
            noticia.a="<article>"+"<h2>"+$("#titulo").val()+"<button class= 'fa fa-trash fa-lg'></button></h2><p>"+$("#textArea").val()+"</p>"+d.getDate()+"-"+d.getMonth()+"-"+d.getFullYear()+"</article>"
            newsArray.push($("aside"),$(""));
        }else{
            alert("Introdueix valors vàlids!")
        }

        //array localStorage

        localStorage.setItem('myArray',JSON.stringify(newsArray));
        newsArray = localStorage.getItem('myArray');

        newsArray = JSON.parse(newsArray);
    
        
     });


    $(".fa fa-trash").click(function(){
        // alert("hola");
        $(this).parent().parent().hide();
         
        
    });
 
    //LOCAL STORAGE
 
    var numVisites;
    
 
    if(localStorage.numVisites){
        localStorage.numVisites = Number(localStorage.numVisites)+1;
    }else {
        localStorage.numVisites = 1;
    }
    $("#visitantes").html("visitant numero: "+JSON.parse(localStorage.numVisites));


    //array noticias

   
   
    

    
    
});

