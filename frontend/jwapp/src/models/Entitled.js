module.exports = function(app){
    app.factory('Entitled', function(EntitledRegistration){
        return function Entitled(){
            var self = this;
            self.id = null;
            self.englishName = null;
            self.arabicName = null;
            self.civilId = null;
            self.mobileNumber1 = null;
            self.mobileNumber2 = null;
            self.emailAddress = null;
            self.entitledRegistration = new EntitledRegistration();
            self.organizationUnit = null;
            self.currentStatus = null;
            self.id = null;
            
            
        }
    });
};