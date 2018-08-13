module.exports = function(app){
    app.run(function(messageFcty,$state,$transitions,$trace,$q){

    // $trace.enable('TRANSITION');
    $state.defaultErrorHandler(function(error) {
        // console.error(error);           
        messageFcty.handleErrorMessage(error.detail); 
      });
    
        $transitions.onSuccess({}, function($transition){
         
            messageFcty.resetMessage();
            messageFcty.resetSuccessMessage();
               // console.log($transition.$from());
            // console.log($transition.$to());
        });
        $transitions.onError({},function($transition){
            
               // return $q.reject();
                         
             //  $transition.abort();
            // $state.go($transition.$from.name);
            // return false;
        });
        
         $transitions.onStart({}, function($transition){
             //console.log($transition.params());
            //  return $q.reject();
            
        });

        // $transitions.onExit({exiting: "stateName"}, function($transition){...});
        
        // $transitions.onRetain({}, function($transition){...});
        
          $transitions.onEnter({}, function($transition){
             
            // $transition.abort();
          });
        
        // $transitions.onFinish({}, function($transition){...});
        
        // $transitions.onSuccess({}, function($transition){...});
      
     
    });
};