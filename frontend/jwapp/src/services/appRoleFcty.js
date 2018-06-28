module.exports = function(app){
    app.factory('appRoleFcty', function(){
        return {
            admin : {
                code : 'ROLE_ADMIN',
                description : ''
            },
            officer : {
                code : 'ROLE_OFFICER',
                description : ''
            },
            user : {
                code : 'ROLE_USER',
                description : ''
            }
        };
    });
};