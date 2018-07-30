module.exports = function(app){
    app.controller('contentViewerCtrl', function(imgSrc){
        var vm = this;
        vm.imgSrc = imgSrc;
        
    });
};