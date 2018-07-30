module.exports = function(app){
    app.factory('EntitledRegistrationChangeStatusRequest', function(EntitledRegistration){
        return function CapacityDelegationChangeStatusRequest(){
            var self = this;
            self.entitledRegistration = new EntitledRegistration();
            self.note = null;
            self.version = 0;
            
            
        }
    });
};