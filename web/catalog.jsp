<%-- 
    Document   : catalog
    Created on : Nov 22, 2016, 12:39:32 AM
    Author     : Cesar
--%>

<script type="text/javascript" src="js/catalog.js"></script>
<!--script type="text/javascript" src="js/plugins/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/plugins/bootstrap.min.js"></script-->
<!--script type="text/javascript" src="js/plugins/main.js"></script-->
<script type="text/javascript" src="js/plugins/modernizr.js"></script>
<!--link type="text/css" href="css/bootstrap.min.css"-->



<link type="text/css" href="css/style.css">
<link type="text/css" href="css/reset.css">



<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="stylesheet" href="css/resetCart.css"> <!-- CSS reset -->
	<link rel="stylesheet" href="css/styleCart.css"> <!-- Gem style -->
        
        <script src="js/plugins/modernizrCart.js"></script> <!-- Modernizr -->
</head>
<!--<div id="cd-cart-trigger">
            <a class="cd-img-replace" href="#0">
                <span>0</span>
            </a>
        </div>-->
<div id="cd-cart-trigger" >
    <a href="#0" class="cd-cart2 items-added">        
        <img  style="z-index: 1" class="cd-cart2 items-added" src="img/cd-icon-cart.svg" alt="Cart">
        <span style="z-index: 5">0</span>
        
    </a> 
    
</div>

<body>
    
    <!--<main><!--COSA DE NEMO**********************************-->
        <ul class="cd-gallery" id="gallery"></ul>
    <!--</main><!--**************************************-->
    <div id="cd-shadow-layer"></div>

    <div id="cd-cart">
        <h2>Cart</h2>
        <ul class="cd-cart-items" id="lista-cart">
            <!--<li>
                <span class="cd-qty">1x</span> Product Name
				<div class="cd-price">$9.99</div>
				<a href="#0" class="cd-item-remove cd-img-replace">Remove</a>
            </li>

            <li>
                <!-- ... --
            </li>-->
        </ul> <!-- cd-cart-items -->

        <div class="cd-cart-total">
            <!--<p>Total <span>$39.96</span></p>-->
        </div> <!-- cd-cart-total -->

        <a href="#0" class="checkout-btn" onclick="newSale()">Checkout</a>
    </div> <!-- cd-cart -->
    
    <script type="text/javascript" src="js/plugins/mainCart.js"></script>
</body>

