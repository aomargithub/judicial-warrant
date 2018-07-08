module.exports = function(app){
    app.directive('preventE', function () {
        return {
          
            restrict: 'A',
            link: function(element) {
            
              element.on('keypress', function(event) {
        
                if ( !isIntegerChar() ) 
                  event.preventDefault();
                
                function isIntegerChar() {
                  return /[0-9]|-/.test(
                    String.fromCharCode(event.which))
                }
        
              })       
            
            }
        };
     });
}