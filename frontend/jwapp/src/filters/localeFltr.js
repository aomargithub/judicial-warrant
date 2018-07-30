module.exports = function(app){
    app.filter('localeFltr', function(){
        return function(item){
            return item.arabicName;
        };
    });
};