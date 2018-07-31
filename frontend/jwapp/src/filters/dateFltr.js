module.exports = function(app){
    app.filter('dateFltr', function($filter){
        var vm =this;
        return function(items, fromDate, toDate) {
            var filtered = [];
    var from_date = Date.parse(fromDate);
    var to_date = Date.parse(toDate);
    if (!to_date || !from_date) {
      return items;
    }
    angular.forEach(items, function(item) {
      if (Date.parse(item.startDate) > from_date && Date.parse(item.startDate) < to_date) {
        filtered.push(item);
      }
    });
    return filtered;
        };
    });
};