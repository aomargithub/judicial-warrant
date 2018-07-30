module.exports = function(app){
    app.factory('webSocket', function(){
 
       var service = {};
 
       service.connect = function() {
         if(service.ws) { return; }
      
         var ws = new WebSocket("ws://localhost:8081");
      
         ws.onopen = function() {
           service.callback("Succeeded to open a connection");
         };
      
         ws.onerror = function() {
           service.callback("Failed to open a connection");
         }
      
         ws.onmessage = function(event) {
           service.callback(event.data);
         };
      
         service.ws = ws;
       }
      
       service.send = function(event) {
         service.ws.send(event);
       }
      
       service.subscribe = function(callback) {
         service.callback = callback;
       }
      
       return service;
    
    })
}