module.exports=function(app){
    app.controller('loginDrtvCtrl', function ($scope, $state, authenticationSrvc){
        $scope.doLogin = function(){
            authenticationSrvc.login($scope.credentials).then(function(status){
                if(status.code === 200){
                    $state.go('home');
                }else{
                    $scope.statusText = status.text;
                }
            });
        };
    });
};