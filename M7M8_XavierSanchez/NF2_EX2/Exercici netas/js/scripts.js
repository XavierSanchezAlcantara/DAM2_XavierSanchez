$(function () {
    var num;
    var newsArray = JSON.parse(localStorage.getItem('news'));
    //printar noticias
    if (JSON.parse(localStorage.getItem('news')) != null) {
        for (var i = 0; i < newsArray.length; i++) {
            var noticia = "<article> <h2>" + newsArray[i].title + " <img  class=\"imag\" width=\"20\" height=\"20\" src=\"imagenes/papelera.png\"/></h2><p>" + newsArray[i].date + "h.<br>" + newsArray[i].content + "</p>";
            if (newsArray[i].seccion == "Principal") {
                $("section").prepend(noticia);
            } else { $("aside").prepend(noticia); }
        }
    } else {
        var newsArray = new Array();
    }
    //mostrar papelera
    $("ul li:first").click(function () {
        $("#formulari").slideToggle("slow");
    });
    //insertar noticia

    $("div button").click(function () {
        if ($("div input").val().length > 0 && $("div textarea").val().length > 0) {
            var news = new Object();
            var d = new Date();

            news.title = $("div input").val();
            news.content = $("div textarea").val();
            news.seccion = $("div select").val();
            news.date = d;

            newsArray.push(news);
            localStorage.setItem('news', JSON.stringify(newsArray));

            var noticia = "<article><h2>" + $("div input").val() + " <img  class=\"imag\" width=\"20\" height=\"20\" src=\"imagenes/papelera.png\"/></h2><p>" + d.getDate() + "/" + d.getMonth() + "/" + d.getFullYear() + ", " + d.getHours() + ":" + d.getMinutes() + "h.<br>" + $("div textarea").val() + "</p>";
            if ($("div select").val() == "Principal") {
                $("section").prepend(noticia);
            } else { $("aside").prepend(noticia); }
        }

        $(".imag").click(function () {
            $(this).parent().parent().hide();
        });

    });

    $("ul li:nth-child(2)").click(function () {
        $(".imag").toggle();
        
    });

    $(".imag").click(function () {
        $(this).parent().parent().hide();
    });
    // visites
    if (localStorage.numvisites) {
        localStorage.numvisites = Number(localStorage.numvisites) + 1;
    } else { localStorage.numvisites = 1 }
    num = JSON.stringify(localStorage.numvisites)
    $("#numvisites").html("visites:" + num);

});