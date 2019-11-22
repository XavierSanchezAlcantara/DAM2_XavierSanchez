$(document).ready(function () {
    $(".boton").on("click", function () {
        $("#formulari").slideToggle();

    });

});

$(document).ready(function () {
    $("#botonNoticia").on("click", function () {
        var value=$('#select').val();
        if(value=="Principal"){
            $("aside").prepend("<article>"+"<h3>"+($("#titulo").val()+"<button class='fa fa-trash fa-lg'></button></h3><p>"+$("#textArea").val())+"</p><article>");
        }else if(value=="Secundari"){
            $("section").prepend("<article>"+"<h2>"+($("#titulo").val()+"<button class='fa fa-trash fa-lg'></button></h2><p>"+$("#textArea").val())+"</p><article>");
        }else{
            alert('No has seleccionat el tipus de noticia!!!!!')
        }
        
        
    

        
    });
    $(".fa-trash").click(function () {
        $(this).parent().parent().hide();
    });

});
