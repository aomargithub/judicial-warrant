module.exports = function(app){
    app.directive('preventE', function () {
        return {
          
            restrict: 'A',
            link: function(scope, element, attrs, ctrl) {
            
              element.on('keydown', function (event) {  
                if (event.which == 64 || event.which == 16) {  
                    // to allow numbers  
                    return true;  
                } else if (event.which >= 48 && event.which <= 57) {  
                    // to allow numbers  
                    return true;  
                } else if (event.which >= 96 && event.which <= 105) {  
                    // to allow numpad number  
                    return true;  
                } else if (event.which == 43) {  
                    // to allow plus   
                    return false;  
                } else if ([8, 13, 27, 37, 38, 39, 40].indexOf(event.which) > -1) {  
                    // to allow backspace, enter, escape, arrows  
                    return true;  
                } else {  
                    event.preventDefault();  
                    // to stop others  
                    return false;  
                }  
            });     
            
            }
        };
     });
}