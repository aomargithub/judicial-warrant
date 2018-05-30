module.exports = function(app){

    app.service('messageBarModeSrvc', function(){
        var self = this;

        self.ERROR = "ERROR";
        self.ERROR_REFETCH = "ERROR_REFETCH";
        self.INFO = "INFO";
    });
};