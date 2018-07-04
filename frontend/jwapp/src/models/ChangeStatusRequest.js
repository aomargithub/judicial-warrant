module.exports = function(app){
    app.factory('ChangeStatusRequest', function(){
        return function EntitledNote(){
            var self = this;
            self.note = null;
            
        }
    });
};