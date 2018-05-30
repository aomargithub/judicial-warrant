module.exports = function(app){
    app.factory('User', function(){
        return function User(){
            var self = this;
            self.id = null;
            self.englishName = null;
            self.arabicName = null;
            self.isActive = false;
            self.mobileNumber1 = null;
            self.mobileNumber2 = null;
            self.emailAddress = null;
            self.civilId = null;
            self.loginName = null;
            self.userType = null;
            self.organizationUnit = null;
            self.role = null;
        }
    });
};