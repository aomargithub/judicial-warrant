module.exports = function(app){
    app.filter('tableFltr', function(){
        var vm =this;
        return function(tableData , keyName){
            
            var output=[], keys=[];
            angular.forEach(tableData,function(item){
                var key=item[keyName];
                if(keys.indexOf(key) === -1){
                    keys.push(key);
                    output.push(item);
                }

            });
            return output;
        };
    });
};