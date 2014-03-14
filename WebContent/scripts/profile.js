$(function(){
    $("#friendsList").hide();
    
    $( ".expand" ).click(function() {
    	$(this).parent().find("div").toggle();
    });

});