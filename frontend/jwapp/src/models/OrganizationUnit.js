module.exports = function(app){
    app.factory('OrganizationUnit', function(){
        return function OrganizationUnit(){
            var self = this;
            self.id = null;
            self.englishName = null;
            self.arabicName = null;
            self.isActive = false;
            self.listOrder = null;
            self.version = null;
        }
    });
};