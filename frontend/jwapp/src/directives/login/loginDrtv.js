module.exports = function(app){
    app.directive('login', function(){
        return {
            controllerAs : 'loginDrtvCtrl',
            controller : 'loginDrtvCtrl',
            template : require('./login-drtv.html')
        };
    });
  };