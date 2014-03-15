$(function(){
    $("#friendsList").hide();
    $("#quizzesMade").hide();
    $("#inbox").hide();
    
    $( ".expand" ).click(function() {
    	$(this).parent().find("div").toggle();
    });

});