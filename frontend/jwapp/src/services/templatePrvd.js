module.exports = function(app){
    app.provider('templateSrvc', function(){
        var self = this;
        self.getTemplate = function(name){
            var template = require('../views/' + name + '.html');
            return template;
        };

        function ReturnObj(){
            this.getTemplate = self.getTemplate;
        };

        self.$get = function(){
            return new ReturnObj();
        };
    });
};