module.exports = function(app){
    app.factory('EntitledRegistration', function(Request, CapacityDelegation){
        return function EntitledRegistration(){
            var self = this;
            self.id = null;
            self.version = null;
            self.request = new Request();
            self.capacityDelegation = null;
            self.requestType = null;
            
            
        }
    });
};