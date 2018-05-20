module.exports = function(app){
    app.controller('requestTypesMenuDrtvCtrl', function(requestTypeSrvc){
        var vm = this;

        requestTypeSrvc.getAll().then(function(response){
            vm.items = response.data;
        });
    });
};