module.exports = function(app){
    app.factory('Request', function(){
        return function Request(){
            var self = this;
            self.organizationUnit=null;
            self.serial=null;
            
        }
    });
};