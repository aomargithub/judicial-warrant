module.exports = function(app){
    app.service('stringUtilSrvc', function(){
        var self = this;
        self.removeQuotes = function(org){
            return org.replace(/["]+/g, '');
        };
    });
}