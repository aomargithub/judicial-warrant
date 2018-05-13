module.exports = function(app){
    app.controller('breadcrumbsDrtvCtrl', function($state, authenticationSrvc,breadcrumbs){
        var vm = this;

        vm.breadcrumb = function(){
            vm.breadcrumbs=breadcrumbs;
          
        }
    });
}; 