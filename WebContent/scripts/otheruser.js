$(function(){
    
    $("#sendMessage").click(function(){
    	event.preventDefault();
        $.ajax({ 
               context:this,
               url: 'SendMessage',
               type: 'POST',
               cache: false, 
               data: { user:$('#sendTo').val(),type:'m',message: $('#messageText').val()  }, 
               success: function(data){
            	  $('#messageText').val('');
            	  $('#sendMessage').html('Message Sent!');
            	  $('#sendMessage').prop("disabled", true);
            }
         });
    });
    
    $("#sendFriendRequest").click(function(){
    	event.preventDefault();
        $.ajax({ 
               context:this,
               url: 'SendMessage',
               type: 'POST',
               cache: false, 
               data: { user:$('#sendTo').val(),type:'f',message: $('#friendRequestText').val()  }, 
               success: function(data){
            	  $('#friendRequestText').val('');
            	  $('#sendFriendRequest').html('Request Sent!');
            	  $('#sendFriendRequest').prop("disabled", true);
            }
         });
    });
    
    $("#sendChallenge").click(function(){
    	event.preventDefault();
        $.ajax({ 
               context:this,
               url: 'SendMessage',
               type: 'POST',
               cache: false, 
               data: { user:$('#sendTo').val(),type:'c',message: $('#challengeText').val(), quizid:$('#quizSelector').val() }, 
               success: function(data){
            	  $('#challengeText').val('');
             	  $('#sendChallenge').html('Challenge Sent!');
             	  $('#sendChallenge').prop("disabled", true);
            }
         });
    });

    
    
});