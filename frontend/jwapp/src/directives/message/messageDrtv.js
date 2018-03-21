module.exports = function(app){
    app.directive('messageDrtv', function(){
        return {
            scope : {
                message: '='
            },
            transclude: true,
            template : require('./message-drtv.html'),
            replace: true
        }
    });
};