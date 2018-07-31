module.exports = function(app){
    app.run(function($rootScope,messageFcty){
        $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){ 
            // alert("root change success");
        })

        $rootScope.$on('$stateChangeStart', function(event, toState, toParams, fromState, fromParams, options){ 
            // alert("root change start");
        })

        $rootScope.$on('$stateChangeError', function(event, toState, toParams, fromState, fromParams, error){ 
            // alert("root change error");
            messageFcty.handleErrorMessage(error);
        })
        $rootScope.$on('$stateNotFound', function(event, transition) {
          
                // console.log(unfoundState.to); // "lazy.state"
                // console.log(unfoundState.toParams); // {a:1, b:2}
                // console.log(unfoundState.options); // {inherit:false} + default options
           
            });
    });
};