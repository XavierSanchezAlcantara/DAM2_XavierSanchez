$(function () {
  var url2 = "https://sheets.googleapis.com/v4/spreadsheets/1o2dz1wVN-Nnj4Ut1dVffUx_anXFlmlUWHZP1ppeo4iQ/values/";
  url2 += "xaviDB!A1:B1?majorDimension=ROWS&key=AIzaSyCPyzW_SVgl6ZDNO09f8Zq3eCNYNZCTC3M";
  $(".fa-bars").on("click", function () {
    $(".nav-side-menu").slideToggle("right");
  $.get(url2, function (dades) {

    console.log(JSON.stringify(dades));
  });


});