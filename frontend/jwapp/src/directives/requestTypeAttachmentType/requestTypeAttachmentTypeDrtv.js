module.exports = function(app){
    app.directive('requestTypeAttachmentType', function(){
        return {
            controllerAs: 'requestTypeAttachmentTypeDrtvCtrl',
            controller: 'requestTypeAttachmentTypeDrtvCtrl',
            template: require('./requestTypeAttachmentType-drtv.html'),
            replace: true
        };
    });
};