module.exports = function(app){
    app.factory('CapacityDelegation', function(){
        return function CapacityDelegation(){
            var self = this;
            self.id = null;
            self.requestType = null;
            self.jobTitle = null;
            self.isActive = false;
            self.ministerialDecreeNumber = null;
            self.version = null;
            self.ministerialDecreeDate = null;
            self.organizationUnit=null;
            
        }
    });
};