module.exports = function(app){
    app.directive('messageDrtv', function(){
        return {
            controllerAs : 'messageDrtvCtrl',
            controller : 'messageDrtvCtrl',
            template : require('./message-drtv.html'),
            replace: true,
            scope : {
                closeCallback: '=',
                refetchCallback : '=',
                editId: '=',
                mode: '='
            }
                }
    });
};