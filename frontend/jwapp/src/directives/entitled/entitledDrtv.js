module.exports = function(app){
    app.directive('entitled', function(){
        return {
            controllerAs : 'entitledDrtvCtrl',
            controller : 'entitledDrtvCtrl',
            template : require('./entitled-drtv.html'),
            replace: true,

            scope : {
                serial: '@',
                noteditable: '@'

            },

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