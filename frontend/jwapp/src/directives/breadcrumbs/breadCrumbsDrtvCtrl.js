module.exports = function(app){
    app.controller('breadCrumbsDrtvCtrl', function($state, authenticationSrvc,breadcrumbs){
        var vm = this;

        vm.breadcrumb = function(){
            vm.breadcrumbs=breadcrumbs;
          
        }
    });
}; 