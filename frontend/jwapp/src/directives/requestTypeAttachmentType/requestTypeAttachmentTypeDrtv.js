module.exports = function(app){
    app.directive('requestTypeAttachmentType', function(){
        return{
            controllerAs: 'requestTypeAttachmentTypeDrtvCtrl',
            controller: 'requestTypeAttachmentTypeDrtvCtrl',
            template: require('./request-type-attachment-type-drtv.html'),
            replace: true 
        };
    });
}