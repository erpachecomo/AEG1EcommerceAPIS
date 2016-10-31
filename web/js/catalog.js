$(function () {
    $.ajax({
        url: 'GetProducts',
        type: 'GET'
        //,
       // dataType: 'json'
    }).done(function (json1) {
            console.log("json:"+json1);
        $.each($.parseJSON(json1.msg), function (i, msg) {
            
            console.log("indice:"+i);
            console.log("objeto:"+msg);
            
            $('<li></li>').attr("id", "li-item" + i).appendTo('#gallery');
            $('<div></div>').attr("id", "div-item" + i).attr("class", "cd-single-item").appendTo('#li-item' + i);
            $('<a></a>').attr("id", "a-item" + i).appendTo('#div-item' + i);
            $('<ul></ul>').attr("class", "cd-slider-wrapper").attr("id", "ul-item" + i).appendTo('#a-item' + i);
            $('<li></li>').attr("class", "selected").attr("id", "li-img-item" + i).appendTo('#ul-item' + i);
            $('<img>').attr("src", 'https://lh3.googleusercontent.com/--BIUhXPpNIk/AAAAAAAAAAI/AAAAAAAAAJY/M6azp0UYZXc/photo.jpg').attr("alt", msg.productname).appendTo('#li-img-item' + i);

            $('<div></div>').attr("id", "div-control" + i).attr("class", "cd-customization row").appendTo('#div-item' + i);
            $('<div></div>').attr("id", "div-form" + i).attr("class", "col-lg-7 col-md-7 col-sm-7").appendTo('#div-control' + i);
            $('<div></div>').attr("id", "div-input-group" + i).attr("class", "input-group").appendTo('#div-form' + i);
            $('<span></span>').attr("id", "span-icon" + i).attr("class", "input-group-addon").appendTo('#div-input-group' + i);
            $('<i></i>').attr("class", "glyphicon glyphicon-plus").appendTo('#span-icon' + i);
            $('<input>').attr("class", "form-control").attr("type", "number").attr("id", "quantity" + i).attr("name", "quantity" + i).attr("placeholder", "Cantidad").appendTo('#div-input-group' + i);

            $('<button></button>').attr("class", "add-to-cart").attr("id", "button-cart" + i).appendTo('#div-control' + i);
            $('<em></em>', {text: "Add to cart"}).appendTo('#button-cart' + i);
            $('<svg></svg>').appendTo('#button-cart' + i)
                    .attr("x", "0px")
                    .attr("y", "0px")
                    .attr("width", "32px")
                    .attr("height", "32px")
                    .attr("viewBox", "0 0 32 32")
                    .attr("id", "svg" + i)
                    ;
            $('<path>').appendTo('#svg' + i)
                    .attr("stroke-dasharray", "19.79 19.79")
                    .attr("stroke-dashoffset", "19.79")
                    .attr("fill", "none")
                    .attr("stroke", "#FFFFFF")
                    .attr("d", "M9,17l3.9,3.9c0.1,0.1,0.2,0.1,0.3,0L23,11")
                    .attr("stroke-width", "2")
                    .attr("stroke-linecap", "square")
                    .attr("stroke-miterlimit", "10")
                    ;
            $('<button></button>', {text: 'Customize'}).attr("class", "cd-customization-trigger").appendTo('#div-item' + i);
            $('<div></div>').attr("class", "cd-item-info").attr("id", "div-cd-item-info" + i).appendTo('#li-item' + i);
            $('<b></b>').attr("id", "b" + i).appendTo('#div-cd-item-info' + i);
            $('<a></a>').appendTo('#b' + i);
            $('<em></em>', {text: msg.currency + " " + msg.salepricemin}).appendTo('#div-cd-item-info' + i);
            //         i++;



        });
    });
});