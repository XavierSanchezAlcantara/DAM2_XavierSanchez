$(function(){
    var url2= "https://sheets.googleapis.com/v4/spreadsheets/1o2dz1wVN-Nnj4Ut1dVffUx_anXFlmlUWHZP1ppeo4iQ/values/";
	url2 += "xaviDB!A1:B1?majorDimension=ROWS&key=AIzaSyCPyzW_SVgl6ZDNO09f8Zq3eCNYNZCTC3M";

    $.get(url2,function(dades){
        
        console.log(JSON.stringify(dades));
    });
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelectorAll('.sidenav');
        var instances = M.Sidenav.init(elems, options);
      });
    
      // Or with jQuery
    
      $(document).ready(function(){
        $('.sidenav').sidenav();
      });
         
});