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
            element.bind('change', function(event){
              //  var files = event.target.files;
             // scope[attrs.ngFile] = loadEvent.target.result;
            $parse(attrs.ngFile).assign(scope,element.files)
            scope.$apply();
            scope.$watch(attrs.ngFile, function(files) {
                element.val(files);
              });
          });
        
         }
        };
     });
}