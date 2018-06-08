module.exports = function(app){
    app.factory('menuItemsFcty', function($rootScope, appRoleFcty){

        function Item(){

            var subItemsValue = [];

            this.codeValue = function(code){
                this.code = code;
                this.title = $rootScope.messages[this.code];
                return this;
            };

            this.classesValue = function(classes){
                this.classes = classes;
                return this;
            };

            this.routeValue = function(route){
                this.route = route;
                return this;
            }

            this.addSubItem = function(subItem){
                subItemsValue.push(subItem);
                return this;
            };

            this.showFilter = function(role){
               

                this.subItems = subItemsValue.filter(function(subItem){
                    return subItem.showFilter(role);
                });
                
                return this.subItems.length > 0 ? true : false;
            };
        }

        function SubItem(){
            
            this.codeValue = function(code){
                this.code = code;
                this.title = $rootScope.messages[this.code];
                return this;
            };

            this.routeValue = function(route){
                this.route = route;
                return this;
            }

            this.showFilterValue = function(showFilter){
                this.showFilter = showFilter;
                return this;
            };
        }

        var lookupsItem = new Item();
        lookupsItem.codeValue('LOOKUPS').routeValue('lookupsSubmenu').classesValue(["glyphicon", "glyphicon-cog"]);

        var organizationUnitsSubItem = new SubItem(), 
        attachmentTypesSubItem = new SubItem(),
        requestTypeAttachmentTypesSubItem = new SubItem();

        organizationUnitsSubItem.codeValue('ORGANIZATION_UNITS')
        .routeValue('.organizationUnits')
        .showFilterValue(function(role){
            return role === appRoleFcty.mojAdmin.code;});

        attachmentTypesSubItem.codeValue('ATTACHMENT_TYPES')
        .routeValue('.attachmentTypes')
        .showFilterValue(function(role){return role === appRoleFcty.mojAdmin.code;});

        requestTypeAttachmentTypesSubItem.codeValue('REQUEST-TYPE-ATTACHMENT-TYPES')
        .routeValue('.requestTypeAttachmentTypes')
        .showFilterValue(function(role){return role === appRoleFcty.mojAdmin.code;});


        
        

        lookupsItem.addSubItem(organizationUnitsSubItem)
                   .addSubItem(attachmentTypesSubItem)
                   .addSubItem(requestTypeAttachmentTypesSubItem);


        
        
         //===================================
        var usersItem = new Item()
        usersItem.codeValue('USERS').routeValue('usersSubmenu').classesValue(["glyphicon", "glyphicon-user"]);
        var internalUsersSubItem = new SubItem(),
        externalUsersSubItem = new SubItem();


        internalUsersSubItem.codeValue('INTERNAL_USERS')
        .routeValue('.internalUsers')
        .showFilterValue(function(role){
            return role === appRoleFcty.mojAdmin.code;
        });

        externalUsersSubItem.codeValue('EXTERNAL_USERS')
        .routeValue('.externalUsers')
        .showFilterValue(function(role){
            return role === appRoleFcty.mojAdmin.code;
        });


        usersItem.addSubItem(internalUsersSubItem)

        .addSubItem(externalUsersSubItem);

        var menu = [lookupsItem, usersItem];

        return {
            items :  menu
        };
    });
};