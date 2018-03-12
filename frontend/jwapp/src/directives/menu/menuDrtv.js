module.exports = function(app){
    app.directive('menu', function(){
        return {
            controllerAs : 'menuDrtvCtrl',
            controller : 'menuDrtvCtrl',
            template : require('./menu-drtv.html'),
            link: function(scope, element){
                element.bind('click', function(e){
                    scope.isMenuOpen = !scope.isMenuOpen;
                    if(scope.isMenuOpen){
                        angular.element(e.target).addClass('active');
                    }else{
                        angular.element(e.target).removeClass('active');
                    }
                });
            },
            replace: true
        };
    });
};