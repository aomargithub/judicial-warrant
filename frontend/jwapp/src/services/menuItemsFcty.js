module.exports = function(app){
    app.factory('menuItemsFcty', function($rootScope, appRoleFcty){
        return {
            items : [
                {
                    code : 'LOOKUPS',
                    get title () {
                        return $rootScope.messages[this.code];
                    },
                    route : 'lookupsSubmenu',
                    classes : [
                        "glyphicon",
                        "glyphicon-cog"
                    ],
                    showFilter : function(role){
                        return role === appRoleFcty.moj.code;
                    },
                    subItems : [
                        {
                            code : 'ORGANIZATION_UNITS',
                            get title () {
                                return $rootScope.messages[this.code];
                            },
                            route : '.organizationUnits',
                            showFilter : function(role){
                                return role === appRoleFcty.client.code;
                            }
                        },
                        {
                            code : 'ATTACHMENT_TYPES',
                            get title () {
                                return $rootScope.messages[this.code];
                            },
                            route : '#',
                            showFilter : function(role){
                                return true;
                            }
                        },
                        {
                            code : 'CANDIDATE_ATTACHMENT_TYPES',
                            get title () {
                                return $rootScope.messages[this.code];
                            },
                            route : '#',
                            showFilter : function(role){
                                return true;
                            }
                        },
                        {
                            code : 'REQUEST_ATTACHMENT_TYPES',
                            get title () {
                                return $rootScope.messages[this.code];
                            },
                            route : '#',
                            showFilter : function(role){
                                return true;
                            }
                        }          
                    ]
                },
                {
                    code : 'USERS',
                    get title () {
                        return $rootScope.messages[this.code];
                    },
                    route : '',
                    showFilter : function(role){
                        return role === appRoleFcty.client.code;
                    }
                }
            ]
        };
    });
};