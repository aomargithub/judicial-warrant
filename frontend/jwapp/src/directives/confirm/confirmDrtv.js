module.exports = function(app){
    app.directive('confirm', function ($uibModal, $parse) {
        
        return {
            link: function(scope, el, attr){
                el.bind('click', function(){
                  var instance = $uibModal.open({
                    template: require('./confirmationDialoge-drtv.html'),
                    controllerAs: 'confirmDrtvCtrl',
                    controller: 'confirmDrtvCtrl',
                  });
                  
                  instance.result.then(function(){
                    // close - action!
                    $parse(attr.onConfirm)(scope);
                  }, 
                  function(){
                    // dimisss - do nothing
                  });
                });
              }
           
        };
     });
}