module.exports = function(app){
    app.factory('RequestTypeAttachmentType', function(){
        return function RequestTypeAttachmentType(){
            var self = this;
            self.id=null;
            self.attachmentType = null;
            self.requestType = null;
            self.isActive = false;
            self.listOrder = null;
            self.version = null;
        }
    })
}