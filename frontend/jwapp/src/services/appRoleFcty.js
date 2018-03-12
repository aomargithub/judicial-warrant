module.exports = function(app){
    app.factory('appRoleFcty', function(){
        return {
            moj : {
                code : 'MOJ',
                description : ''
            },
            mla : {
                code : 'MLA',
                description : ''
            },
            client : {
                code : 'CLIENT',
                description : ''
            },
            jti : {
                code : 'JTI',
                description : ''
            }
        };
    });
};