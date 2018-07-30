module.exports = function(app){
    app.controller('requestTypesMenuDrtvCtrl', function(requestTypeSrvc, appSessionSrvc){
        var vm = this;
        vm.items = [];
        vm.role = appSessionSrvc.getCurrentUser().role;

        requestTypeSrvc.getAll().then(function(response){
        if(vm.role === 'ROLE_OFFICER' || vm.role === 'ROLE_USER') {
            response.data.forEach(function(rt, index) {
                if(rt.code === 'CAPACITY_DELEGATION' && vm.role === 'ROLE_OFFICER') {
                        vm.items.push(rt);
                }
                if(rt.code === 'ENTITLED_REGISTRATION' && vm.role === 'ROLE_USER') {
                    vm.items.push(rt);
                }  
            });
        }

        });
    });
};