module.exports = function(app){
    app.directive('menu', function(){
        return {
            controllerAs : 'menuDrtvCtrl',
            controller : 'menuDrtvCtrl',
            template : require('./menu-drtv.html'),
            link: function(scope, element, attrs, menuDrtvCtrl){
                element.bind('click', function(e){
                    menuDrtvCtrl.toggleMenuState();
                    var target = angular.element(e.target);
                 
                    if(!target.hasClass('menu-toggle')){
                        return;
                    }

                   while(target[0].id != 'sidebar'){
                        target = target.parent();
                    }

                    if(menuDrtvCtrl.getMenuState()){
                        target.addClass('active');
                        
                    }else{
                        target.removeClass('active');
                    }
                });
            },
            replace: true
        };
    });
};