module.exports = function(app){
    app.factory('RequestAttachment', function(){
        return function RequestAttachment(){
            var self = this;
            self.id = null;
            self.file = null;
            self.attachmentType = null;
            self.ucmDocumentId=null;
            self.documentName=null;
            
        }
    });
};