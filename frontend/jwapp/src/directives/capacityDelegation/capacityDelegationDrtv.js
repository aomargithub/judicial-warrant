module.exports = function(app){
    app.directive('capacityDelegation', function(){
        return {
            controllerAs : 'capacityDelegationDrtvCtrl',
            controller : 'capacityDelegationDrtvCtrl',
            template : require('./capacityDelegation-drtv.html'),
            replace: true
        }; 
    }).directive('ngFile', function ($parse) {
        return {
           restrict: 'A',
           link: function(scope, element, attrs) {
            element.bind('change', function(){
       
            $parse(attrs.ngFile).assign(scope,element[0].files[0])
            scope.$apply();
          });
         }
        };
     });
}