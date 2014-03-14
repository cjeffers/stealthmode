$(function(){
    $("#createannouncement").hide();
    $("#userremove").hide();
    $("#quizremove").hide();
    $("#historyremove").hide();
    $("#stats").hide();
    
    $( ".expand" ).click(function() {
    	$(this).parent().find("div").toggle();
    });
    
    $("#newannouncement").click(function(){
    	event.preventDefault();
        $.ajax({ 
               context:this,
               url: 'PostAnnouncement',
               type: 'POST',
               cache: false, 
               data: { announcement:$('#announcement').val() }, 
               success: function(data){
            	   $('#announcement').val("");
            }
         });
    });
    
    $(".deleteUserButton").click(function(){
    	event.preventDefault();
        $.ajax({ 
               context:this,
               url: 'DeleteUser',
               type: 'POST',
               cache: false, 
               data: { id:$(this).attr('id') }, 
               success: function(data){
            	   $(this).closest('li').remove();
            }
         });
    });
    
    $(".deleteQuizHistoryButton").click(function(){
    	event.preventDefault();
        $.ajax({ 
               context:this,
               url: 'DeleteQuizHistory',
               type: 'POST',
               cache: false, 
               data: { id:$(this).attr('id') }, 
               success: function(data){
            	   alert("Quiz History Deleted");
            }
         });
    });
    
    
    
});