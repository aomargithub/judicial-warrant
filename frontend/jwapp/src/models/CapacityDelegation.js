module.exports = function(app){
    app.factory('CapacityDelegation', function(Request){
        return function CapacityDelegation(){
            var self = this;
            self.id = null;
            self.jobTitle = null;
            self.isActive = false;
            self.ministerialDecreeNumber = null;
            self.version = null;
            self.ministerialDecreeDate = new Date('YYYY-MM-DD');
            self.request = new Request();
            self.requestType = null;
            
            
        }
    });
};