module.exports = function(app){
    app.directive('paginationDrtv', function(){
        return {
            replace: true,
            template: require('./pagination-drtv.html'),
            controller: 'paginationDrtvCtrl',
            controllerAs: 'paginationDrtvCtrl',           
            bindToController : true,
            scope : {
                itemsSize: '=',
                page: '='
            }
        };
    });
};