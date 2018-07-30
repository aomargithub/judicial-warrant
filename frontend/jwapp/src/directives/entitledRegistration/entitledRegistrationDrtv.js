module.exports = function(app){
    app.directive('entitledRegistration', function(){
        return {
            controllerAs : 'entitledRegistrationDrtvCtrl',
            controller : 'entitledRegistrationDrtvCtrl',
            template : require('./entitledRegistration-drtv.html'),
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