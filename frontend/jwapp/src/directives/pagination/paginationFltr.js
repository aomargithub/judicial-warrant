module.exports = function(app){
    app.filter('paginationFltr', function(){
        return function(items, startIndex, endIndex){
            if (angular.isUndefined(items) || angular.isUndefined(items.slice)) {
                return;
            }
            return items.slice(startIndex, endIndex + 1);
        };
    });
};