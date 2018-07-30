module.exports = function(app){
    app.directive('attachmentType', function(){
        return {
            controllerAs: 'attachmentTypeDrtvCtrl',
            controller: 'attachmentTypeDrtvCtrl',
            template: require('./attachmentType-drtv.html'),
            replace: true
        };
    });
};