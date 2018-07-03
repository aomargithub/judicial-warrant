module.exports = function(app){
    app.factory('CapacityDelegationChangeStatusRequest', function(CapacityDelegation){
        return function CapacityDelegationChangeStatusRequest(){
            var self = this;
            self.capacityDelegation = new CapacityDelegation();
            self.note = null;
            self.version = 0;
            
            
        }
    });
};