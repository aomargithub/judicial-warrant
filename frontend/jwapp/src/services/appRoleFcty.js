module.exports = function(app){
    app.factory('appRoleFcty', function(){
        return {
            mojAdmin : {
                code : 'MOJ_ADMIN',
                description : ''
            },
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