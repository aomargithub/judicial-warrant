module.exports = function(app){
    app.controller('confirmDrtvCtrl', function($scope, $uibModalInstance){
        var vm=this;
        vm.ok = function(){
            $uibModalInstance.close();
          };
          vm.cancel = function(){
            $uibModalInstance.dismiss();
          };
    });
}