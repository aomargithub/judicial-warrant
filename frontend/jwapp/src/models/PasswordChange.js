module.exports = function(app){
    app.factory('PasswordChange', function(){
        return function PasswordChange(){
            var self = this;
            self.oldPassword = null;
            self.newPassword = null;
            self.confirmNewPassword = null;
        }
    });
};