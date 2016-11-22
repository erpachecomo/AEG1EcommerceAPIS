
$(function () {
    $.ajax({
        url: 'webresources/product/listProducts',
        type: 'GET',
        async: false, 
        dataType: 'json'
    }).done(function (json1) {
        
        $.each($.parseJSON(json1.msg), function (i, msg) {
            $('<li></li>').attr("id", "li-item" + i).appendTo('#gallery');
            $('<div></div>').attr("id", "div-item" + i).attr("class", "cd-single-item").appendTo('#li-item' + i);
            $('<a></a>').attr("id", "a-item" + i).appendTo('#div-item' + i);
            $('<ul></ul>').attr("class", "cd-slider-wrapper").attr("id", "ul-item" + i).appendTo('#a-item' + i);
            $('<li></li>').attr("class", "selected").attr("id", "li-img-item" + i).appendTo('#ul-item' + i);
            $('<img>').attr("src", msg.image.split("/")[0] + "/" + msg.image.split("/")[1])
                    .attr("alt", msg.productname).appendTo('#li-img-item' + i);

            $('<div></div>').attr("id", "div-control" + i).attr("class", "cd-customization row").appendTo('#div-item' + i);
            $('<div></div>').attr("id", "div-form" + i).attr("class", "col-lg-7 col-md-7 col-sm-7").appendTo('#div-control' + i);
            $('<div></div>').attr("id", "div-input-group" + i).attr("class", "input-group").appendTo('#div-form' + i);
            //$('<span></span>').attr("id", "span-icon" + i).attr("class", "input-group-addon").appendTo('#div-input-group' + i);
            //$('<i></i>').attr("class", "glyphicon glyphicon-plus").appendTo('#span-icon' + i);
            $('<input>').attr("class", "form-control").attr("min", '1').attr("max", "'" + msg.stock + "'").attr("type", "number").attr("id", "quantity" + i).attr("name", "quantity" + i).attr("placeholder", "Cantidad").appendTo('#div-input-group' + i);
            //console.log("
            //oCart("+msg.productid+",$('#quantity" + i+"').val())");
            
            $('<button></button>')
                    .attr("onclick", "addToCart('"+msg.productname+"','"+msg.code+"','"+msg.productid+"','"+msg.salepricemin+"','"+msg.image+"','" +i + "',"+"'button-cart" + i+"','3')")
                    .attr("class", "add-to-cart").attr("id", "button-cart" + i).appendTo('#div-control' + i);
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
            $('<a></a>', {text: msg.productname}).appendTo('#b' + i);
            $('<em></em>', {text: msg.currency + " " + msg.salepricemin}).appendTo('#div-cd-item-info' + i);
            //         i++;



        });
    });
     /*$.ajax({
        url: 'GetCart',
        type: 'GET',
        dataType: 'json',
    }).done(function (json) {
        console.log(json);
        updateCart(json);
    });*/
});

function addToCart(productname, code, productid, salepricemin, image, quantity, btn,userid) {
    var animating=false;
    var button = $("#"+btn);
    if (!animating) {
        //animate if not already animating
        animating = true;
        //resetCustomization(button);

        button.addClass('is-added').find('path').eq(0).animate({
            //draw the check icon
            'stroke-dashoffset': 0
        }, 300, function () {
            setTimeout(function () {
                button.removeClass('is-added').find('em').on('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function () {
                    //wait for the end of the transition to reset the check icon
                    button.find('path').eq(0).css('stroke-dashoffset', '19.79');
                    animating = false;
                });

                if ($('.no-csstransitions').length > 0) {
                    // check if browser doesn't support css transitions
                    button.find('path').eq(0).css('stroke-dashoffset', '19.79');
                    animating = false;
                }
            }, 600);
        });
    }
    var data =
            '{"productname":"'+ productname+'","productcode":"'+ code+'","productid":"'+productid+'","productquantity":"'+ $("#quantity"+quantity).val()+'", "productprice":"'+ salepricemin+'","productimage":"'+ image+'","userid":"'+userid +'"}';
    console.log(data);
            
    $.ajax({
        url: 'webresources/product/addProductToCart',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'json',
        data: data
    }).done(function (json) {
        console.log(json);
        updateCart(json);
    });
}
function updateCart(json) {
    var cart = $('.cd-cart2');
    (!cart.hasClass('items-added')) && cart.addClass('items-added');

    var cartItems = cart.find('span'),
            text = Object.keys(json).length;
    cartItems.text(text);
    var $total = 0;
    $('#lista-cart').empty();
    $('.cd-cart-total').empty();
    $.each(json, function (i, msg) {
        
        var addtoCart = $('.cd-cart-items');
        var $item = $('<li> <span class="cd-qty">' + msg.quantity + ' </span>' + msg.productname + ' \n\
                            <div class="cd-price"> $' + msg.quantity * msg.productprice + ' </div> \n\
                            <a class="cd-item-remove cd-img-replace" onclick="removeCart(\''+msg.productcode+'\')" > Remove </a></li>');
        $($item).attr('id', 'li-cart').appendTo('#lista-cart');
        $total = $total + (msg.quantity * msg.productprice);
    });
    $total = $('<p>Total<span id="totalSale">'+$total+'</span></p>');
    $($total).appendTo('.cd-cart-total');
            
}

function removeCart(code){
    /*var data = {
        "code": code
    }; 
        console.log("khastapasanda1!"+code);
    $.ajax({
        url: 'RemoveCart',
        type: 'POST',
        dataType: 'json',
        data: data
    }).done(function (json) {
        console.log("khastapasanda!"+json);
        updateCart(json);
    });*/
}

function newSale(){
    var data={
        "userid": 3,
        "amount": document.getElementById("totalSale").innerHTML
    }
    //console.log(data);
    $.ajax({
        url: 'NewSale',
        type: 'POST',
        dataType: 'json',
        data: data
    }).done(function (data) {
        if(data.code==200){
            $.growl.notice({message:data.msg});
        }else{
            $.growl.notice({message:data.msg});
        }
    }).fail(function(){
        $.growl.notice({message:"El servidor no está disponible 😞"});
    });
}