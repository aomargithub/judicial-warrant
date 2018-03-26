module.exports = function(app){
    app.controller('paginationDrtvCtrl', function($scope, pageSize){
        var vm = this, numberOfPages = 0;
       
        vm.$onInit = function(){
            $scope.$watch(function(){
                return vm.itemsSize
            }, function(newValue){
                if(!angular.isUndefined(newValue)){
                    numberOfPages = Math.ceil(newValue / pageSize);
                    vm.pages = Array.from({length : numberOfPages}, (x, i) => i+1);

                    if(vm.currentPage > numberOfPages){
                        vm.currentPage--;
                        setStartAndEnd();
                    }
                }
            });           
            vm.currentPage = 1;
            setStartAndEnd();
        };

        var setStartAndEnd = function(){
            vm.page.start = (vm.currentPage -1) * pageSize;
            vm.page.end = vm.page.start + (pageSize - 1);
        }

        vm.next = function(){
            if(vm.currentPage < numberOfPages){
                vm.currentPage++;
                setStartAndEnd();
            }
        };
        
        vm.last = function(){
            vm.currentPage = numberOfPages;
            setStartAndEnd();
        };

        vm.previous = function(){
            if(vm.currentPage > 1){
                vm.currentPage--;
                setStartAndEnd();
            }            
        };

        vm.first = function(){
            vm.currentPage = 1;
            setStartAndEnd();
        };

        vm.goTo = function(){
            setStartAndEnd();
        };
    });
};