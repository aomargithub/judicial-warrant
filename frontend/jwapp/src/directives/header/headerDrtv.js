module.exports = function(app){
    app.directive('header', function(){
        return {
            template: require('./header-drtv.html')
        }
    });
};