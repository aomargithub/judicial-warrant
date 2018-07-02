module.exports = function(app){
    app.service('modalSrvc', function($uibModal){
        var vm = this;
       
        vm.viewContent = function(response){

            var file = new Blob([response.data], {type: response.headers('Content-Type')});
            var reader = new FileReader();
               
            reader.onload = (function(f) {
                return function(e) {
                   var imgSrc = e.target.result;
                   var modalInstance = $uibModal.open({
                        animation: true,
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        template: require('./content-viewer/content-viewer-mdl.html'),
                        controller: 'contentViewerCtrl',
                        controllerAs: 'contentViewerCtrl',
                        size: 'lg',
                        resolve: {
                        imgSrc: function () {
                            return imgSrc;
                        }
                        }
                    });
                };
            })(file);
            reader.readAsDataURL(file);
        };
    });
};