module.exports = function(app){
    app.factory('AttachmentType', function(){
        return function AttachmentType(){
            var self = this;
            self.id = null;
            self.englishName = null;
            self.arabicName = null;
            self.isActive = false;
            self.listOrder = null;
            self.version = null;
            self.isMandatory = false;
            self.isEntitledAttachment = false;
        }
    });
};