$(function(){
    $('#nav a').on('click',function(e){
        e.preventDefault();
        var page = $(this).attr('href');
        
        $('#content').load(page);
        var cerrar = $('#cs').attr('href');
        console.log(page);
        console.log(cerrar);
        if(page===cerrar){
          $(location).attr('href', '/AEEcommerce/login.html');   
        }
        $('#nav li').removeClass('active');
        $(this).parent().addClass('active');
    });
    
});
