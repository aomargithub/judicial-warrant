module.exports = function(app){
    app.directive('messageDrtv', function(){
        return {
            controllerAs : 'messageDrtvCtrl',
            controller: function(){
                
            },
            bindToController : true,
            scope : {
                message: '=',
                closeCallback: '=',
                refetchCallback : '=',
                editId: '=',
                mode: '=',
                withlink : '@',
                discription : '@'
            },
            template : require('./message-drtv.html'),
            replace: true
        }
    });
};