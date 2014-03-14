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
               data: { id:$(this).attr('id') }, 
               success: function(data){
                $(this).find('i:first').removeClass('liked').addClass('unliked');
                $(this).removeClass("altunlikeable").addClass("altlikeable");
                $(this).closest('td').find('h4').text(parseInt($(this).closest('td').find('h4').text()) - 1);
                //$(element).parent().find('span:first').empty().html("<i class='fa fa-exclamation'></i>");
            }
         })
    });
    
    
    
});