module.exports = function(app){
    app.factory('EntitledAttachment', function(Entitled){
        return function EntitledAttachment(){
            var self = this;
            self.id = null;
            self.file = null;
            self.attachmentType = null;
            self.ucmDocumentId=null;
            self.fileName=null;
            self.entitled = new Entitled();
            
        }
    });
};