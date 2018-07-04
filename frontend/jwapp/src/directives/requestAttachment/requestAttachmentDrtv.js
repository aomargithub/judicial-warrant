module.exports = function(app){
    app.directive('requestAttachment', function(){
        return {
            controllerAs : 'requestAttachmentDrtvCtrl',
            controller : 'requestAttachmentDrtvCtrl',
            template : require('./requestAttachment-drtv.html'),
            replace: true,

            scope : {
                urlperfix: '@',
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