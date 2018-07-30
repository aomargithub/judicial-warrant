module.exports = function(app){
    app.directive('entitledTrainning', function(){
        return {
            controllerAs: 'entitledTrainningDrtvCtrl',
            controller: 'entitledTrainningDrtvCtrl',
            template: require('./entitledTrainning-drtv.html'),
            replace: true
        };
    });
}