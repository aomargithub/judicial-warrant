module.exports = function(app){
    app.directive('menuHeader', function(){
        return {
            template: require('./menu-header-drtv.html'),
            require: '^^menu',
            link: function(scope, element, attrs, menuDrtvCtrl){
               /* element.bind('click', function(e){
                    menuDrtvCtrl.toggleMenuState();
                    if(menuDrtvCtrl.getMenuState()){
                        angular.element(e.target).parent().parent().addClass('active');
                        console.log('if', angular.element(e.target).parent().parent());
                    }else{
                        angular.element(e.target).parent().parent().removeClass('active');
                        console.log('else', angular.element(e.target).parent().parent());
                    }
                });*/
            },
            replace: true
        };
    });
};